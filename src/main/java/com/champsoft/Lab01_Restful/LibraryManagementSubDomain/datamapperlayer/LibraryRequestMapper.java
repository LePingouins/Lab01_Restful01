package com.champsoft.Lab01_Restful.LibraryManagementSubDomain.datamapperlayer;

import com.champsoft.Lab01_Restful.LibraryManagementSubDomain.dataaccesslayer.Library;
import com.champsoft.Lab01_Restful.LibraryManagementSubDomain.presentationlayer.LibraryRequestModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LibraryRequestMapper {

    Library requestModelToEntity(LibraryRequestModel libraryRequestModel);
}
