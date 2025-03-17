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
    private Integer id;

    @Embedded
    private LibrarianIdentifier librarianIdentifier;

    private String firstName;
    private String lastName;
    private String email;
    private Double salary;

    // Ensure this matches the database type
    @Column(name = "library_id")
    private String libraryId; // This should be a String if library_id is VARCHAR in the database

    @ManyToOne
    @JoinColumn(name = "library_id", referencedColumnName = "library_id", insertable = false, updatable = false)
    private Library library; // This should be the only mapping for library_id
}
