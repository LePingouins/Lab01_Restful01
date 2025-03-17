package com.champsoft.Lab01_Restful.LibraryManagementSubDomain.dataaccesslayer;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Embeddable  // Indique que cette classe est un objet de valeur à être intégré dans d'autres entités
@Getter
@Setter
public class LibraryIdentifier {

    @Column(name = "library_id")
    private String libraryId; // Identifiant unique pour la bibliothèque

    // Le constructeur par défaut génère un nouvel UUID pour l'identifiant de la bibliothèque
    public LibraryIdentifier() {
        this.libraryId = UUID.randomUUID().toString();
    }

    // Constructeur pour utiliser un identifiant de bibliothèque existant
    public LibraryIdentifier(String libraryId) {
        this.libraryId = libraryId;
    }
}
