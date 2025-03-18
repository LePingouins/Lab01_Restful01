package com.champsoft.Lab01_Restful.LibraryManagementSubDomain.businesslogiclayer;

import com.champsoft.Lab01_Restful.LibraryManagementSubDomain.presentationlayer.LibraryRequestModel;
import com.champsoft.Lab01_Restful.LibraryManagementSubDomain.presentationlayer.LibraryResponseModel;

import java.util.List;

public interface LibraryService {

    List<LibraryResponseModel> getAllLibraries();
    LibraryResponseModel getLibraryById(String libraryId);

    LibraryResponseModel addLibrary(LibraryRequestModel newLibraryData);

    LibraryResponseModel updateLibrary(String libraryId, LibraryRequestModel newLibraryData);

    String deleteLibraryById(String libraryId);
}
