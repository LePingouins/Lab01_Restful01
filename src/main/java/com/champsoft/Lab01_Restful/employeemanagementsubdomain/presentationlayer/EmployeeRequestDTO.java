package com.champsoft.Lab01_Restful.employeemanagementsubdomain.presentationlayer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EmployeeRequestDTO {

    private String employeeId;
    private String firstName;
    private String lastName;
    private LocalDate dob;
    private String email;
    private String title;
    private double salary;




}
