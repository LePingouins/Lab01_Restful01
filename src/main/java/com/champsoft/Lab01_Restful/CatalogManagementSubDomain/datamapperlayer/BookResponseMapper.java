package com.champsoft.Lab01_Restful.CatalogManagementSubDomain.datamapperlayer;

import com.champsoft.Lab01_Restful.CatalogManagementSubDomain.dataaccesslayer.Book;
import com.champsoft.Lab01_Restful.CatalogManagementSubDomain.presentationlayer.BookResponseModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BookResponseMapper {
    @Mapping(expression = "java(book.getBookIdentifier().getBookId())", target = "bookId")
    BookResponseModel entityToResponseModel(Book book);

    @Mapping(expression = "java(book.getBookIdentifier().getBookId())", target = "bookId")
    List<BookResponseModel> entityListToResponseModelList(List<Book> books);
}
