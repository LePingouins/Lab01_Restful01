package com.champsoft.Lab01_Restful.LibraryManagementSubDomain.dataaccesslayer;

import com.champsoft.Lab01_Restful.CatalogManagementSubDomain.dataaccesslayer.Book;
import com.champsoft.Lab01_Restful.StaffManagementSubDomain.dataaccesslayer.Librarian;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity
@Table(name="libraries")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Library {

    @Id
    @Column(name = "library_id", nullable = false, unique = true) // Unique identifier for the library
    private String libraryId; // This should be a UUID or a unique string

    private String name; // Name of the library
    private String address; // Address of the library
    private Integer maxCapacity; // Maximum capacity of the library

    // One-to-many relationship with Librarian
    @OneToMany(mappedBy = "library", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Librarian> librarians; // List of librarians associated with this library

    // Embedded identifier for additional library identification details
    @Embedded
    private LibraryIdentifier libraryIdentifier; // Ensure this is present if you want to use it

    // One-to-many relationship with Book
    @OneToMany(mappedBy = "library", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Book> books; // List of books associated with this library
}