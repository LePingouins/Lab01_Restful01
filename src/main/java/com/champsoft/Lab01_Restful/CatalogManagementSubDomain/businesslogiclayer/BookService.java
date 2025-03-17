package com.champsoft.Lab01_Restful.CatalogManagementSubDomain.businesslogiclayer;

import com.champsoft.Lab01_Restful.CatalogManagementSubDomain.presentationlayer.BookRequestModel;
import com.champsoft.Lab01_Restful.CatalogManagementSubDomain.presentationlayer.BookResponseModel;

import java.util.List;

public interface BookService {

    List<BookResponseModel> getAllBooks();
    BookResponseModel getBookbyId(String bookId);

    BookResponseModel addBook(BookRequestModel newBookData);

    BookResponseModel updateBook(String bookId, BookRequestModel newBookData);

    String deleteBookbyId(String bookId);
}
