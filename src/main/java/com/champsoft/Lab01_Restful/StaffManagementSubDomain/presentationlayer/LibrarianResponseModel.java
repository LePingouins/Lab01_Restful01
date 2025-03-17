package com.champsoft.Lab01_Restful.StaffManagementSubDomain.presentationlayer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LibrarianResponseModel {
    private String librarianId;
    private String firstName;
    private String lastName;
    private String email;
    private Double salary;
}
