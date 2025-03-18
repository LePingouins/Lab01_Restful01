package com.champsoft.Lab01_Restful.LibraryManagementSubDomain.businesslogiclayer;

import com.champsoft.Lab01_Restful.CatalogManagementSubDomain.dataaccesslayer.Book;
import com.champsoft.Lab01_Restful.LibraryManagementSubDomain.dataaccesslayer.Library;
import java.util.List;

public interface LibraryBookService {
    // Ajouter un livre à la bibliothèque
    Book addBookToLibrary(String libraryId, Book book);

    // Obtenir tous les livres d'une bibliothèque
    List<Book> getBooksByLibrary(String libraryId);

    // Supprimer un livre d'une bibliothèque
    void removeBookFromLibrary(String libraryId, String bookId);

    // Mettre à jour un livre dans la bibliothèque
    Book updateBookInLibrary(String libraryId, String bookId, Book book);
}
