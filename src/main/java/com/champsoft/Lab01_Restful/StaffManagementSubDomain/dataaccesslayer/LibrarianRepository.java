package com.champsoft.Lab01_Restful.StaffManagementSubDomain.dataaccesslayer;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LibrarianRepository extends JpaRepository<Librarian, Integer> {

    Librarian findByLibrarianIdentifier_LibrarianId(String librarianId); // Method to find by librarianId

    void deleteByLibrarianIdentifier_LibrarianId(String librarianId);
}
