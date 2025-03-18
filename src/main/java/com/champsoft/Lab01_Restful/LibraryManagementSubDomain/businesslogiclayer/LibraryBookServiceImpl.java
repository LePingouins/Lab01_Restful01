package com.champsoft.Lab01_Restful.LibraryManagementSubDomain.businesslogiclayer;

import com.champsoft.Lab01_Restful.CatalogManagementSubDomain.dataaccesslayer.Book;
import com.champsoft.Lab01_Restful.CatalogManagementSubDomain.dataaccesslayer.BookRepository;
import com.champsoft.Lab01_Restful.LibraryManagementSubDomain.dataaccesslayer.Library;
import com.champsoft.Lab01_Restful.LibraryManagementSubDomain.dataaccesslayer.LibraryRepository;
import com.champsoft.Lab01_Restful.LibraryManagementSubDomain.businesslogiclayer.LibraryBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibraryBookServiceImpl implements LibraryBookService {

    private final LibraryRepository libraryRepository;
    private final BookRepository bookRepository;

    @Autowired
    public LibraryBookServiceImpl(LibraryRepository libraryRepository, BookRepository bookRepository) {
        this.libraryRepository = libraryRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public Book addBookToLibrary(String libraryId, Book book) {
        Library library = libraryRepository.findByLibraryIdentifier_LibraryId(libraryId);
        if (library != null) {
            book.setLibrary(library); // Lier la bibliothèque au livre
            return bookRepository.save(book);
        } else {
            throw new IllegalArgumentException("Library not found with the provided ID.");
        }
    }

    @Override
    public List<Book> getBooksByLibrary(String libraryId) {
        Library library = libraryRepository.findByLibraryIdentifier_LibraryId(libraryId);
        if (library != null) {
            return bookRepository.findByLibrary(library); // Récupérer les livres par bibliothèque
        } else {
            throw new IllegalArgumentException("Library not found with the provided ID.");
        }
    }

    @Override
    public void removeBookFromLibrary(String libraryId, String bookId) {
        Library library = libraryRepository.findByLibraryIdentifier_LibraryId(libraryId);
        Book book = bookRepository.findByBookIdentifier_BookId(bookId);
        if (library != null && book != null) {
            book.setLibrary(null); // Retirer la bibliothèque du livre
            bookRepository.save(book); // Sauvegarder le livre sans la bibliothèque
        } else {
            throw new IllegalArgumentException("Library or Book not found.");
        }
    }

    @Override
    public Book updateBookInLibrary(String libraryId, String bookId, Book book) {
        Library library = libraryRepository.findByLibraryIdentifier_LibraryId(libraryId);
        Book existingBook = bookRepository.findByBookIdentifier_BookId(bookId);

        if (library != null && existingBook != null) {
            existingBook.setTitle(book.getTitle());
            existingBook.setAuthor(book.getAuthor());
            existingBook.setGenre(book.getGenre());
            existingBook.setIsbn(book.getIsbn());
            existingBook.setCopyAvailable(book.getCopyAvailable());

            // Si nécessaire, lier le livre à une autre bibliothèque
            existingBook.setLibrary(library);

            return bookRepository.save(existingBook);
        } else {
            throw new IllegalArgumentException("Library or Book not found.");
        }
    }
}
