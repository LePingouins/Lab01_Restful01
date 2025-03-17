package com.champsoft.Lab01_Restful.LibraryManagementSubDomain.presentationlayer;

import com.champsoft.Lab01_Restful.LibraryManagementSubDomain.businesslogiclayer.LibraryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/libraries")
public class LibraryController {
    private final LibraryServiceImpl libraryServiceImpl;

    @Autowired
    public LibraryController(LibraryServiceImpl libraryServiceImpl) {
        this.libraryServiceImpl = libraryServiceImpl;
    }

    // Endpoint to get all libraries
    @GetMapping
    public ResponseEntity<List<LibraryResponseModel>> getAllLibraries() {
        return ResponseEntity.ok().body(libraryServiceImpl.getAllLibraries());
    }

    // Endpoint to get a specific library by its ID
    @GetMapping("/{libraryId}")
    public ResponseEntity<LibraryResponseModel> getLibraryById(@PathVariable String libraryId) {
        return ResponseEntity.ok().body(libraryServiceImpl.getLibraryById(libraryId));
    }

    // Endpoint to add a new library
    @PostMapping
    public ResponseEntity<LibraryResponseModel> addLibrary(@RequestBody LibraryRequestModel newLibraryData) {
        return ResponseEntity.status(HttpStatus.CREATED).body(libraryServiceImpl.addLibrary(newLibraryData));
    }

    // Endpoint to update an existing library
    @PutMapping("/{libraryId}")
    public ResponseEntity<LibraryResponseModel> updateLibrary(@PathVariable String libraryId, @RequestBody LibraryRequestModel newLibraryData) {
        return ResponseEntity.ok().body(libraryServiceImpl.updateLibrary(libraryId, newLibraryData));
    }

    // Endpoint to delete a library by its ID
    @DeleteMapping("/{libraryId}")
    public ResponseEntity<String> deleteLibraryById(@PathVariable String libraryId) {
        return ResponseEntity.ok().body(libraryServiceImpl.deleteLibraryById(libraryId));
    }
}
