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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Embedded
    private LibraryIdentifier libraryIdentifier;

    private String name;
    private String address;
    private Integer maxCapacity;

    // Relation One-to-Many avec Book
    @OneToMany(mappedBy = "library", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Book> books;  // Ajout de la relation avec les livres

    // Relation One-to-Many avec Librarian
    @OneToMany(mappedBy = "library", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Librarian> librarians;
}
