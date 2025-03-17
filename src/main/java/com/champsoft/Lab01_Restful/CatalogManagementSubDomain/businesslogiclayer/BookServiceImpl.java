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
        // Validate the incoming data
        if (newBookData.getIsbn() == null || newBookData.getIsbn().isEmpty()) {
            throw new IllegalArgumentException("ISBN cannot be null or empty");
        }

        // Create a new Book entity from the request model
        Book newBook = new Book();
        newBook.setTitle(newBookData.getTitle());
        newBook.setAuthor(newBookData.getAuthor());
        newBook.setGenre(newBookData.getGenre());
        newBook.setIsbn(newBookData.getIsbn()); // Set the ISBN
        newBook.setCopieAvailable(newBookData.getCopyAvailable());

        // Create a new BookIdentifier and set it in the Book entity
        BookIdentifier bookIdentifier = new BookIdentifier(); // This will generate a new UUID
        newBook.setBookIdentifier(bookIdentifier); // Assuming your Book entity has a setBookIdentifier method

        // Save the book to the repository
        Book savedBook = this.bookRepository.save(newBook);

        // Convert the saved book to a response model
        return this.bookResponseMapper.entityToResponseModel(savedBook);
    }

    @Override
    public BookResponseModel updateBook(String bookId, BookRequestModel updatedBookData) {
        // Find the existing book by its bookId
        Book existingBook = this.bookRepository.findByBookIdentifier_BookId(bookId);
        if (existingBook == null) {
            throw new NotFoundException("Book with id: " + bookId + " not found.");
        }

        // Update the fields of the existing book
        existingBook.setTitle(updatedBookData.getTitle());
        existingBook.setAuthor(updatedBookData.getAuthor());
        existingBook.setGenre(updatedBookData.getGenre());
        existingBook.setIsbn(updatedBookData.getIsbn());
        existingBook.setCopieAvailable(updatedBookData.getCopyAvailable());

        // Save the updated book to the repository
        Book savedBook = this.bookRepository.save(existingBook);

        // Convert the saved book to a response model
        return this.bookResponseMapper.entityToResponseModel(savedBook);
    }

    @Override
    public String deleteBookbyId(String bookId){

        // Crée un BookIdentifier à partir du bookId

        // Recherche du livre
        Book foundBook = this.bookRepository.findByBookIdentifier_BookId(bookId);

        if (foundBook == null){
            return "Book with id: " + bookId + " not found.";
        } else {
            this.bookRepository.delete(foundBook);
            return "Book with id: " + bookId + " deleted successfully.";
        }
    }
}
