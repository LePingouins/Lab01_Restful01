package com.champsoft.Lab01_Restful.StaffManagementSubDomain.datamapperlayer;

import com.champsoft.Lab01_Restful.StaffManagementSubDomain.dataaccesslayer.Librarian;
import com.champsoft.Lab01_Restful.StaffManagementSubDomain.presentationlayer.LibrarianResponseModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LibrarianResponseMapper {

    @Mapping(expression = "java(librarian.getLibrarianIdentifier().getLibrarianId())", target = "librarianId")
    LibrarianResponseModel entityToResponseModel(Librarian librarian);

    @Mapping(expression = "java(librarian.getLibrarianIdentifier().getLibrarianId())", target = "librarianId")
    List<LibrarianResponseModel> entityListToResponseModelList(List<Librarian> librarians);
}
