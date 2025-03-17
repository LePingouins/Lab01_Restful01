package com.champsoft.Lab01_Restful.CatalogManagementSubDomain.dataaccesslayer;

import com.champsoft.Lab01_Restful.LibraryManagementSubDomain.dataaccesslayer.Library;
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

    // Relation Many-to-One avec Library
    @ManyToOne
    @JoinColumn(name = "library_id", nullable = false) // Clé étrangère vers Library
    private Library library; // La bibliothèque à laquelle appartient le livre
}
