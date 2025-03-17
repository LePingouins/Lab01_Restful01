package com.champsoft.Lab01_Restful.CatalogManagementSubDomain.datamapperlayer;

import com.champsoft.Lab01_Restful.CatalogManagementSubDomain.dataaccesslayer.Book;
import com.champsoft.Lab01_Restful.CatalogManagementSubDomain.presentationlayer.BookRequestModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookRequestMapper {

    Book requestModelToEntity(BookRequestModel bookRequestModel);
}
