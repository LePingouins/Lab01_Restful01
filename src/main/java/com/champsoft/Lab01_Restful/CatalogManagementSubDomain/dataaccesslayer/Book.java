package com.champsoft.Lab01_Restful.CatalogManagementSubDomain.dataaccesslayer;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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
    private int copieAvailable;

    // Relation 1-N avec Option via ElementCollection
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "book_options", joinColumns =
    @JoinColumn(name = "book_id"))
    private List<Option> options; // Liste des options pour le livre

    public Book(BookIdentifier bookIdentifier, String title, String author, String genre, String isbn, int copieAvailable, List<Option> options) {
        this.bookIdentifier = bookIdentifier;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.isbn = isbn;
        this.copieAvailable = copieAvailable;
        this.options = options;
    }

    public Book(String title, String author, String genre, String isbn, int copieAvailable) {
        this.bookIdentifier = new BookIdentifier();
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.isbn = isbn;
        this.copieAvailable = copieAvailable;
    }
}
