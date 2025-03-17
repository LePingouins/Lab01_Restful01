package com.champsoft.Lab01_Restful.CatalogManagementSubDomain.dataaccesslayer;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "books")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // Clé primaire unique pour chaque livre

    @Embedded
    private BookIdentifier bookIdentifier; // Identifiant public du livre (UUID)

    private String title;
    private String author;
    private String genre;
    private String isbn;
    @Column(name = "copy_available")
    private int copyAvailable;
}
