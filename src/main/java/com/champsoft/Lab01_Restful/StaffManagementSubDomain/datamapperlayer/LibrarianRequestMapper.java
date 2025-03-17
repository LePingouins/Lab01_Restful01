package com.champsoft.Lab01_Restful.StaffManagementSubDomain.datamapperlayer;

import com.champsoft.Lab01_Restful.StaffManagementSubDomain.dataaccesslayer.Librarian;
import com.champsoft.Lab01_Restful.StaffManagementSubDomain.presentationlayer.LibrarianRequestModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LibrarianRequestMapper {

    @Mapping(target = "library.id", source = "libraryId") // Ajout du lien avec Library
    Librarian requestModelToEntity(LibrarianRequestModel librarianRequestModel);
}

