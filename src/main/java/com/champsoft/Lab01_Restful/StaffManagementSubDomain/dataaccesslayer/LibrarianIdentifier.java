package com.champsoft.Lab01_Restful.StaffManagementSubDomain.dataaccesslayer;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Embeddable
@Getter
@Setter
public class LibrarianIdentifier {

    @Column(name = "librarian_id")
    private String librarianId;

    public LibrarianIdentifier() {
        this.librarianId = UUID.randomUUID().toString();
    }

    public LibrarianIdentifier(String librarianId) {
        this.librarianId = librarianId;
    }
}
