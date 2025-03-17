package com.champsoft.Lab01_Restful.LibraryManagementSubDomain.dataaccesslayer;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

}
