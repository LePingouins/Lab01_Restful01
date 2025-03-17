package com.champsoft.Lab01_Restful.StaffManagementSubDomain.dataaccesslayer;

import com.champsoft.Lab01_Restful.LibraryManagementSubDomain.dataaccesslayer.Library;
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

    // Relation Many-to-One avec Library
    @ManyToOne
    @JoinColumn(name = "library_id", nullable = false) // Clé étrangère vers Library
    private Library library;
}
