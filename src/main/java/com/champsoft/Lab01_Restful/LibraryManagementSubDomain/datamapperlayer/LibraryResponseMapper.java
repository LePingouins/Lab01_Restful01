package com.champsoft.Lab01_Restful.LibraryManagementSubDomain.datamapperlayer;

import com.champsoft.Lab01_Restful.LibraryManagementSubDomain.dataaccesslayer.Library;
import com.champsoft.Lab01_Restful.LibraryManagementSubDomain.presentationlayer.LibraryResponseModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LibraryResponseMapper {

    @Mapping(expression = "java(library.getLibraryIdentifier().getLibraryId())", target = "libraryId")
    @Mapping(source = "librarians", target = "librarians") // Inclure les librarians dans la réponse
    @Mapping(source = "books", target = "books") // Ajouter les books dans la réponse
    LibraryResponseModel entityToResponseModel(Library library);

    @Mapping(expression = "java(library.getLibraryIdentifier().getLibraryId())", target = "libraryId")
    @Mapping(source = "librarians", target = "librarians")
    @Mapping(source = "books", target = "books")
    List<LibraryResponseModel> entityListToResponseModelList(List<Library> libraries);
}
