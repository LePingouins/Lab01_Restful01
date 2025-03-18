package com.champsoft.Lab01_Restful.LibraryManagementSubDomain.dataaccesslayer;

import com.champsoft.Lab01_Restful.CatalogManagementSubDomain.dataaccesslayer.Book;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "libraries")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Library {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // Clé primaire unique pour chaque bibliothèque

    @Embedded
    private LibraryIdentifier libraryIdentifier; // Identifiant public de la bibliothèque (UUID)

    private String name;
    private String address;
    private String city;
    private String state;
    private String postalCode;

    // Relation One-to-Many avec Book
    @OneToMany(mappedBy = "library", cascade = CascadeType.ALL)
    private List<Book> books; // Liste des livres associés à la bibliothèque
}
