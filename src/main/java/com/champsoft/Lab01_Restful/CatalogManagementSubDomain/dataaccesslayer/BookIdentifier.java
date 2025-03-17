package com.champsoft.Lab01_Restful.CatalogManagementSubDomain.dataaccesslayer;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Embeddable  // Marks it as a value object to be embedded in an entity
@Getter
@Setter

public class BookIdentifier {
    @Column(name = "book_id")

    private String bookId;

    public BookIdentifier() {
        this.bookId = UUID.randomUUID().toString();
    }

    public BookIdentifier(String bookId) {
        this.bookId = bookId;
    }
}
