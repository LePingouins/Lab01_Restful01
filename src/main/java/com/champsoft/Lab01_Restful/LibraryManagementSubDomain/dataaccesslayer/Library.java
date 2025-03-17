package com.champsoft.Lab01_Restful.LibraryManagementSubDomain.dataaccesslayer;

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
    private Integer id; // Identifiant privé unique pour chaque bibliothèque

    @Embedded
    private LibraryIdentifier libraryIdentifier; // Identifiant public de la bibliothèque (UUID)

    private String name;      // Nom de la bibliothèque
    private String address;   // Adresse de la bibliothèque
    private Integer maxCapacity;  // Capacité maximale de la bibliothèque

    // Relation One-to-Many avec Librarian
    @OneToMany(mappedBy = "library", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Librarian> librarians;
}
