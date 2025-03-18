package com.champsoft.Lab01_Restful.LibraryManagementSubDomain.dataaccesslayer;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Embeddable
@Getter
@Setter
public class LibraryIdentifier {

    @Column(name = "library_id") // ✅ Change "library_id" en "library_uuid"
    private String libraryId;

    public LibraryIdentifier() {
        this.libraryId = UUID.randomUUID().toString();
    }

    public LibraryIdentifier(String libraryId) {
        this.libraryId = libraryId;
    }
}
