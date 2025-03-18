package com.champsoft.Lab01_Restful.LibraryManagementSubDomain.presentationlayer;

import com.champsoft.Lab01_Restful.LibraryManagementSubDomain.businesslogiclayer.LibraryServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/v1/libraries")
public class LibraryController {

    private final LibraryServiceImpl libraryServiceImpl;

    public LibraryController(LibraryServiceImpl libraryServiceImpl) {
        this.libraryServiceImpl = libraryServiceImpl;
    }

    @GetMapping
    public ResponseEntity<List<LibraryResponseModel>> getLibraries() {
        return ResponseEntity.ok().body(libraryServiceImpl.getAllLibraries());
    }

    @GetMapping("/{libraryId}")
    public ResponseEntity<LibraryResponseModel> getLibraryById(@PathVariable String libraryId) {
        return ResponseEntity.ok().body(this.libraryServiceImpl.getLibraryById(libraryId));
    }

    @PostMapping
    public ResponseEntity<LibraryResponseModel> addLibrary(@RequestBody LibraryRequestModel newLibraryData) {
        // Validate the incoming data
        if (newLibraryData.getName() == null || newLibraryData.getName().isEmpty()) {
            return ResponseEntity.badRequest().body(null); // Return 400 Bad Request if validation fails
        }

        // Call the service to add the library
        LibraryResponseModel createdLibrary = this.libraryServiceImpl.addLibrary(newLibraryData);

        // Return a 201 Created response with the created library data
        return ResponseEntity.status(HttpStatus.CREATED).body(createdLibrary);
    }

    @PutMapping("/{libraryId}")
    public ResponseEntity<LibraryResponseModel> updateLibrary(@PathVariable String libraryId, @RequestBody LibraryRequestModel updatedLibraryData) {
        return ResponseEntity.ok().body(libraryServiceImpl.updateLibrary(libraryId, updatedLibraryData));
    }

    @DeleteMapping("/{libraryId}")
    public ResponseEntity<String> deleteLibraryById(@PathVariable String libraryId) {
        String message = this.libraryServiceImpl.deleteLibraryById(libraryId);
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }
}
