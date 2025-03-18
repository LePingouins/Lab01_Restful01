package com.champsoft.Lab01_Restful.CatalogManagementSubDomain.presentationlayer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BookRequestModel {
     private String bookId;
     private String title;
     private String author;
     private String genre;
     private String isbn;
     private int copyAvailable;
     //private String libraryId; // Identifiant de la bibliothèque associée
}
