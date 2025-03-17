package com.champsoft.Lab01_Restful.StaffManagementSubDomain.dataaccesslayer;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Embeddable  // Indicating that this is a value object that will be embedded in other entities
@Getter
@Setter
public class LibrarianIdentifier {

    @Column(name = "librarian_id")
    private String librarianId; // Unique identifier for the librarian

    // Default constructor generates a new UUID for the librarian
    public LibrarianIdentifier() {
        this.librarianId = UUID.randomUUID().toString();
    }

    // Constructor for using an existing librarian ID
    public LibrarianIdentifier(String librarianId) {
        this.librarianId = librarianId;
    }
}
