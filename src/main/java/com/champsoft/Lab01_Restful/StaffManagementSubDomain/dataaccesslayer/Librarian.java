package com.champsoft.Lab01_Restful.StaffManagementSubDomain.dataaccesslayer;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="librarians")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Librarian {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // Private identifier

    @Embedded
    private LibrarianIdentifier librarianIdentifier; // Embedded as a value object

    private String firstName;
    private String lastName;
    private String email;
    private Double salary;


}
