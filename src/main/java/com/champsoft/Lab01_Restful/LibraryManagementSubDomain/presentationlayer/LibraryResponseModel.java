package com.champsoft.Lab01_Restful.LibraryManagementSubDomain.presentationlayer;

import com.champsoft.Lab01_Restful.CatalogManagementSubDomain.presentationlayer.BookResponseModel;
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
    private String city;
    private String state;
    private String postalCode;
    private List<BookResponseModel> books; // Liste des livres associés à la bibliothèque
}
