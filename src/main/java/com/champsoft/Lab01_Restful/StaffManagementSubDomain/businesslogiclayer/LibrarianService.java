package com.champsoft.Lab01_Restful.StaffManagementSubDomain.businesslogiclayer;

import com.champsoft.Lab01_Restful.StaffManagementSubDomain.presentationlayer.LibrarianRequestModel;
import com.champsoft.Lab01_Restful.StaffManagementSubDomain.presentationlayer.LibrarianResponseModel;

import java.util.List;

public interface LibrarianService {

    List<LibrarianResponseModel> getAllLibrarians();

    LibrarianResponseModel getLibrarianById(String librarianId);

    LibrarianResponseModel addLibrarian(LibrarianRequestModel newLibrarianData);

    LibrarianResponseModel updateLibrarian(String librarianId, LibrarianRequestModel updatedLibrarianData);

    String deleteLibrarianById(String librarianId);
}
