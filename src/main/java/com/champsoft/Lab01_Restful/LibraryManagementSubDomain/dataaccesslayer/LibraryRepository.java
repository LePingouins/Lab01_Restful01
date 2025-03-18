package com.champsoft.Lab01_Restful.LibraryManagementSubDomain.dataaccesslayer;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LibraryRepository extends JpaRepository<Library, Integer> {

    Library findByLibraryIdentifier_LibraryId(String libraryId); // Method to find by libraryId
    void deleteByLibraryIdentifier_LibraryId(String libraryId); // Method to delete by libraryId
}
