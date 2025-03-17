package com.champsoft.Lab01_Restful.StaffManagementSubDomain.datamapperlayer;

import com.champsoft.Lab01_Restful.StaffManagementSubDomain.dataaccesslayer.Librarian;
import com.champsoft.Lab01_Restful.StaffManagementSubDomain.presentationlayer.LibrarianRequestModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LibrarianRequestMapper {

    Librarian requestModelToEntity(LibrarianRequestModel librarianRequestModel);
}
