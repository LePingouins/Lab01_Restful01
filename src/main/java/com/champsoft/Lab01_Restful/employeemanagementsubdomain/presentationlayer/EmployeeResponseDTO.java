package com.champsoft.Lab01_Restful.employeemanagementsubdomain.presentationlayer;

import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
public class EmployeeResponseDTO {
    private String employeeId;
    private String firstName;
    private String lastName;
    private LocalDate dob;
    private int age;
    private String email;
    private String title;


}

