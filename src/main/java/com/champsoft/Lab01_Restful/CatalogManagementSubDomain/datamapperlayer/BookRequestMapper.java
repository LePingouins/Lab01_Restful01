package com.champsoft.Lab01_Restful.CatalogManagementSubDomain.datamapperlayer;

import com.champsoft.Lab01_Restful.CatalogManagementSubDomain.dataaccesslayer.Book;
import com.champsoft.Lab01_Restful.CatalogManagementSubDomain.presentationlayer.BookRequestModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BookRequestMapper {

    // Conversion de BookRequestModel en Book (entité)
   // @Mapping(target = "library.libraryId", source = "libraryId")
    Book requestModelToEntity(BookRequestModel bookRequestModel);
}
