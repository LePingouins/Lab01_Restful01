package com.champsoft.Lab01_Restful.LibraryManagementSubDomain.presentationlayer;

import com.champsoft.Lab01_Restful.CatalogManagementSubDomain.presentationlayer.BookResponseModel;
import com.champsoft.Lab01_Restful.StaffManagementSubDomain.presentationlayer.LibrarianResponseModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LibraryResponseModel {
    private String libraryId;
    private String name;
    private String address;
    private Integer maxCapacity;
    private List<LibrarianResponseModel> librarians; // Liste des bibliothécaires associés
    private List<BookResponseModel> books; // Liste des livres associés
}
