package com.champsoft.Lab01_Restful.LibraryManagementSubDomain.dataaccesslayer;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
}
