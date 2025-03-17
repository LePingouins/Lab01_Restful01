package com.champsoft.Lab01_Restful.StaffManagementSubDomain.presentationlayer;

import com.champsoft.Lab01_Restful.StaffManagementSubDomain.businesslogiclayer.LibrarianServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/librarians")
public class LibrarianController {
    private final LibrarianServiceImpl librarianServiceImpl;

    @Autowired
    public LibrarianController(LibrarianServiceImpl librarianServiceImpl) {
        this.librarianServiceImpl = librarianServiceImpl;
    }

    @GetMapping
    public ResponseEntity<List<LibrarianResponseModel>> getAllLibrarians() {
        return ResponseEntity.ok().body(librarianServiceImpl.getAllLibrarians());
    }

    @GetMapping("/{librarianId}")
    public ResponseEntity<LibrarianResponseModel> getLibrarianById(@PathVariable String librarianId) {
        return ResponseEntity.ok().body(librarianServiceImpl.getLibrarianById(librarianId));
    }

    @PostMapping
    public ResponseEntity<LibrarianResponseModel> addLibrarian(@RequestBody LibrarianRequestModel newLibrarianData) {
        return ResponseEntity.status(HttpStatus.CREATED).body(librarianServiceImpl.addLibrarian(newLibrarianData));
    }

    @PutMapping("/{librarianId}")
    public ResponseEntity<LibrarianResponseModel> updateLibrarian(@PathVariable String librarianId, @RequestBody LibrarianRequestModel newLibrarianData) {
        return ResponseEntity.ok().body(librarianServiceImpl.updateLibrarian(librarianId, newLibrarianData));
    }

    @DeleteMapping("/{librarianId}")
    public ResponseEntity<String> deleteLibrarianById(@PathVariable String librarianId) {
        return ResponseEntity.ok().body(librarianServiceImpl.deleteLibrarianById(librarianId));
    }

    // Endpoint to get all librarians of a specific library
    @GetMapping("/library/{libraryId}")
    public ResponseEntity<List<LibrarianResponseModel>> getLibrariansByLibrary(@PathVariable String libraryId) {
        List<LibrarianResponseModel> librarians = librarianServiceImpl.getLibrariansByLibrary(libraryId);
        return ResponseEntity.ok().body(librarians);
    }
}
