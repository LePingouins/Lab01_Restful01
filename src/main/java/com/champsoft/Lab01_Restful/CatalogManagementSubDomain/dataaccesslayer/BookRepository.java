package com.champsoft.Lab01_Restful.CatalogManagementSubDomain.dataaccesslayer;

import com.champsoft.Lab01_Restful.CatalogManagementSubDomain.dataaccesslayer.Book;
import com.champsoft.Lab01_Restful.CatalogManagementSubDomain.dataaccesslayer.BookIdentifier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer> {

   Book findByBookIdentifier_BookId(BookIdentifier bookId);



   // Trouver un livre par son ID
   Book findByBookIdentifier_BookId(String bookId);
}
