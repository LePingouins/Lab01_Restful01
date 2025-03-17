package com.champsoft.Lab01_Restful.LibraryManagementSubDomain.datamapperlayer;

import com.champsoft.Lab01_Restful.LibraryManagementSubDomain.dataaccesslayer.Library;
import com.champsoft.Lab01_Restful.LibraryManagementSubDomain.presentationlayer.LibraryRequestModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LibraryRequestMapper {

    @Mapping(target = "libraryIdentifier.libraryId", source = "libraryId") // Ajout du mapping pour lier le `libraryId`
    Library requestModelToEntity(LibraryRequestModel libraryRequestModel);
}
