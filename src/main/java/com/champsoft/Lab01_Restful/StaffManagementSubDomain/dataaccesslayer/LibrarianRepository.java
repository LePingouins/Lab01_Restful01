package com.champsoft.Lab01_Restful.StaffManagementSubDomain.dataaccesslayer;

import com.champsoft.Lab01_Restful.LibraryManagementSubDomain.dataaccesslayer.LibraryIdentifier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LibrarianRepository extends JpaRepository<Librarian, Integer> {
    // Recherche un bibliothécaire par son identifiant de bibliothécaire
    Librarian findLibrarianByLibrarianIdentifier(LibrarianIdentifier librarianIdentifier);

    // Recherche des bibliothécaires par l'identifiant de la bibliothèque associée
    List<Librarian> findByLibrary_LibraryIdentifier(LibraryIdentifier libraryIdentifier);
}
