package com.champsoft.Lab01_Restful.CatalogManagementSubDomain.dataaccesslayer;

import com.champsoft.Lab01_Restful.CatalogManagementSubDomain.dataaccesslayer.Book;
import com.champsoft.Lab01_Restful.CatalogManagementSubDomain.dataaccesslayer.BookIdentifier;
import com.champsoft.Lab01_Restful.LibraryManagementSubDomain.dataaccesslayer.Library;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {


   List<Book> findByLibrary(Library library);

   Book findByBookIdentifier_BookId(String bookId); // Method to find by bookId
   void deleteByBookIdentifier_BookId(String bookId);
}
