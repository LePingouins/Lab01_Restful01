package com.champsoft.Lab01_Restful.LibraryManagementSubDomain.datamapperlayer;

import com.champsoft.Lab01_Restful.LibraryManagementSubDomain.dataaccesslayer.Library;
import com.champsoft.Lab01_Restful.LibraryManagementSubDomain.presentationlayer.LibraryRequestModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LibraryRequestMapper {

    @Mapping(target = "libraryIdentifier.libraryId", source = "libraryId") // Lier `libraryId` avec `libraryIdentifier`
    Library requestModelToEntity(LibraryRequestModel libraryRequestModel);
}
