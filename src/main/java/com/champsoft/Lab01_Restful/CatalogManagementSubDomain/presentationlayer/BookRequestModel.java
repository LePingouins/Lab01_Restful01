package com.champsoft.Lab01_Restful.CatalogManagementSubDomain.presentationlayer;

import com.champsoft.Lab01_Restful.CatalogManagementSubDomain.dataaccesslayer.Option;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BookRequestModel {
     String bookId;
     String title;
     String author;
     String genre;
     String isbn;
     int copieAvailable;
     List<Option> options;


}
