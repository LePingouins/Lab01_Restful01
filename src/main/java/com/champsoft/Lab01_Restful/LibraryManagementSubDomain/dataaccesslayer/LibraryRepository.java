package com.champsoft.Lab01_Restful.LibraryManagementSubDomain.dataaccesslayer;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LibraryRepository extends JpaRepository<Library, Integer> {
    // Recherche une bibliothèque par son identifiant de bibliothèque
    Library findLibraryByLibraryIdentifier(LibraryIdentifier libraryIdentifier);

    // Recherche une bibliothèque par l'identifiant de la bibliothèque (String)
    Library findLibraryByLibraryIdentifier_LibraryId(String libraryId);
}
