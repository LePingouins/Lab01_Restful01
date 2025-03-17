package com.champsoft.Lab01_Restful.CatalogManagementSubDomain.businesslogiclayer;

import com.champsoft.Lab01_Restful.CatalogManagementSubDomain.dataaccesslayer.Book;
import com.champsoft.Lab01_Restful.CatalogManagementSubDomain.dataaccesslayer.BookIdentifier;
import com.champsoft.Lab01_Restful.CatalogManagementSubDomain.datamapperlayer.BookRequestMapper;
import com.champsoft.Lab01_Restful.CatalogManagementSubDomain.datamapperlayer.BookResponseMapper;
import com.champsoft.Lab01_Restful.CatalogManagementSubDomain.presentationlayer.BookRequestModel;
import com.champsoft.Lab01_Restful.CatalogManagementSubDomain.presentationlayer.BookResponseModel;
import com.champsoft.Lab01_Restful.CatalogManagementSubDomain.dataaccesslayer.BookRepository;
import com.champsoft.Lab01_Restful.utils.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class BookServiceImpl implements BookService{

    private final BookRepository bookRepository;
    private final BookRequestMapper bookRequestMapper;
    private final BookResponseMapper bookResponseMapper;

    @Autowired
        public BookServiceImpl(BookRepository bookRepository, BookRequestMapper bookRequestMapper, BookResponseMapper bookResponseMapper){
        this.bookRepository = bookRepository;
        this.bookRequestMapper = bookRequestMapper;
        this.bookResponseMapper = bookResponseMapper;
    }

    @Override
    public List<BookResponseModel> getAllBooks(){
        List<Book> books = bookRepository.findAll();
        return bookResponseMapper.entityListToResponseModelList(books);
    }

    @Override
    public BookResponseModel getBookbyId(String book_id) {
        // Call the method that accepts a String
        Book book = this.bookRepository.findByBookIdentifier_BookId(book_id);

        if (book == null) {
            throw new NotFoundException("Book with id: " + book_id + " not found.");
        } else {
            return this.bookResponseMapper.entityToResponseModel(book);
        }
    }

    @Override
    public BookResponseModel addBook(BookRequestModel newBookData) {
        // Générer un identifiant unique si `bookId` est null ou vide
        String bookId = (newBookData.getBookId() == null || newBookData.getBookId().isEmpty())
                ? UUID.randomUUID().toString()
                : newBookData.getBookId();

        BookIdentifier bookIdentifier = new BookIdentifier(bookId);

        // Vérifier si le livre existe déjà
        Book foundBook = this.bookRepository.findByBookIdentifier_BookId(bookIdentifier);
        if (foundBook != null) {
            throw new IllegalArgumentException("Book with ID " + bookId + " already exists.");
        }

        // Mapper la requête vers une entité et lui affecter un identifiant
        Book book = this.bookRequestMapper.requestModelToEntity(newBookData);
        book.setBookIdentifier(bookIdentifier);

        // Sauvegarder l'entité dans la base de données
        Book savedBook = this.bookRepository.save(book);

        // Retourner la réponse
        return this.bookResponseMapper.entityToResponseModel(savedBook);
    }

    @Override
    public BookResponseModel updateBook(String bookId, BookRequestModel newBookData){

        // Crée un BookIdentifier à partir du bookId
        BookIdentifier bookIdentifier = new BookIdentifier(bookId);

        // Recherche du livre
        Book foundBook = this.bookRepository.findByBookIdentifier_BookId(bookIdentifier);

        if (foundBook == null){
            throw new NotFoundException("Book with id: " + bookId + " not found.");
        } else {
            // Mise à jour du livre
            Book book = this.bookRequestMapper.requestModelToEntity(newBookData);
            book.setBookIdentifier(bookIdentifier); // important
            book.setId(foundBook.getId());  // Réassigner l'ID existant au livre

            Book savedBook = this.bookRepository.save(book);
            return this.bookResponseMapper.entityToResponseModel(savedBook);
        }
    }

    @Override
    public String deleteBookbyId(String bookId){

        // Crée un BookIdentifier à partir du bookId
        BookIdentifier bookIdentifier = new BookIdentifier(bookId);

        // Recherche du livre
        Book foundBook = this.bookRepository.findByBookIdentifier_BookId(bookIdentifier);

        if (foundBook == null){
            return "Book with id: " + bookId + " not found.";
        } else {
            this.bookRepository.delete(foundBook);
            return "Book with id: " + bookId + " deleted successfully.";
        }
    }
}
