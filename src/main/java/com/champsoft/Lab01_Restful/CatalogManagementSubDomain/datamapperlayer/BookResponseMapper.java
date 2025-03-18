package com.champsoft.Lab01_Restful.CatalogManagementSubDomain.datamapperlayer;

import com.champsoft.Lab01_Restful.CatalogManagementSubDomain.dataaccesslayer.Book;
import com.champsoft.Lab01_Restful.CatalogManagementSubDomain.presentationlayer.BookResponseModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BookResponseMapper {

    @Mapping(expression = "java(book.getBookIdentifier().getBookId())", target = "bookId")
    @Mapping(expression = "java(book.getLibrary().getLibraryIdentifier().getLibraryId())", target = "libraryId") // Lier la bibliothèque au modèle de réponse
    BookResponseModel entityToResponseModel(Book book);

    @Mapping(expression = "java(book.getBookIdentifier().getBookId())", target = "bookId")
    @Mapping(expression = "java(book.getLibrary().getLibraryIdentifier().getLibraryId())", target = "libraryId") // Lier la bibliothèque au modèle de réponse
    List<BookResponseModel> entityListToResponseModelList(List<Book> books);
}
