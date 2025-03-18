package com.champsoft.Lab01_Restful.LibraryManagementSubDomain.presentationlayer;

import com.champsoft.Lab01_Restful.CatalogManagementSubDomain.dataaccesslayer.Book;
import com.champsoft.Lab01_Restful.CatalogManagementSubDomain.datamapperlayer.BookRequestMapper;
import com.champsoft.Lab01_Restful.CatalogManagementSubDomain.datamapperlayer.BookResponseMapper;
import com.champsoft.Lab01_Restful.CatalogManagementSubDomain.presentationlayer.BookRequestModel;
import com.champsoft.Lab01_Restful.CatalogManagementSubDomain.presentationlayer.BookResponseModel;
import com.champsoft.Lab01_Restful.LibraryManagementSubDomain.businesslogiclayer.LibraryBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/libraries/{libraryId}/books")
public class LibraryBookController {

    private final LibraryBookService libraryBookService;
    private final BookRequestMapper bookRequestMapper;
    private final BookResponseMapper bookResponseMapper;

    @Autowired
    public LibraryBookController(LibraryBookService libraryBookService, BookRequestMapper bookRequestMapper, BookResponseMapper bookResponseMapper) {
        this.libraryBookService = libraryBookService;
        this.bookRequestMapper = bookRequestMapper;
        this.bookResponseMapper = bookResponseMapper;
    }

    // Ajouter un livre à la bibliothèque
    @PostMapping
    public ResponseEntity<BookResponseModel> addBookToLibrary(@PathVariable String libraryId, @RequestBody BookRequestModel bookRequestModel) {
        Book book = bookRequestMapper.requestModelToEntity(bookRequestModel);
        Book savedBook = libraryBookService.addBookToLibrary(libraryId, book);
        BookResponseModel responseModel = bookResponseMapper.entityToResponseModel(savedBook);
        return new ResponseEntity<>(responseModel, HttpStatus.CREATED);
    }

    // Obtenir tous les livres d'une bibliothèque
    @GetMapping
    public ResponseEntity<List<BookResponseModel>> getBooksByLibrary(@PathVariable String libraryId) {
        List<Book> books = libraryBookService.getBooksByLibrary(libraryId);
        List<BookResponseModel> responseModels = bookResponseMapper.entityListToResponseModelList(books);
        return new ResponseEntity<>(responseModels, HttpStatus.OK);
    }

    // Supprimer un livre d'une bibliothèque
    @DeleteMapping("/{bookId}")
    public ResponseEntity<Void> removeBookFromLibrary(@PathVariable String libraryId, @PathVariable String bookId) {
        libraryBookService.removeBookFromLibrary(libraryId, bookId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Mettre à jour un livre dans la bibliothèque
    @PutMapping("/{bookId}")
    public ResponseEntity<BookResponseModel> updateBookInLibrary(@PathVariable String libraryId, @PathVariable String bookId, @RequestBody BookRequestModel bookRequestModel) {
        Book book = bookRequestMapper.requestModelToEntity(bookRequestModel);
        Book updatedBook = libraryBookService.updateBookInLibrary(libraryId, bookId, book);
        BookResponseModel responseModel = bookResponseMapper.entityToResponseModel(updatedBook);
        return new ResponseEntity<>(responseModel, HttpStatus.OK);
    }
}
