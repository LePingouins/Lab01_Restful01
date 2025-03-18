package com.champsoft.Lab01_Restful.LibraryManagementSubDomain.presentationlayer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LibraryRequestModel {
    private String libraryId;
    private String name;
    private String address;
    private String city;
    private String state;
    private String postalCode;
}
