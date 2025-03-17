package com.champsoft.Lab01_Restful.LibraryManagementSubDomain.dataaccesslayer;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LibraryRepository extends JpaRepository<Library, Integer> {
    // Méthode pour rechercher une bibliothèque par son identifiant
    Library findLibraryByLibraryIdentifier(LibraryIdentifier libraryIdentifier);

    // Méthode pour rechercher une bibliothèque par son identifiant de bibliothèque (String)
    Library findLibraryByLibraryIdentifier_LibraryId(String libraryId);
}
