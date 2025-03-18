package com.champsoft.Lab01_Restful.LibraryManagementSubDomain.businesslogiclayer;

import com.champsoft.Lab01_Restful.LibraryManagementSubDomain.dataaccesslayer.Library;
import com.champsoft.Lab01_Restful.LibraryManagementSubDomain.dataaccesslayer.LibraryIdentifier;
import com.champsoft.Lab01_Restful.LibraryManagementSubDomain.dataaccesslayer.LibraryRepository;
import com.champsoft.Lab01_Restful.LibraryManagementSubDomain.datamapperlayer.LibraryRequestMapper;
import com.champsoft.Lab01_Restful.LibraryManagementSubDomain.datamapperlayer.LibraryResponseMapper;
import com.champsoft.Lab01_Restful.LibraryManagementSubDomain.presentationlayer.LibraryRequestModel;
import com.champsoft.Lab01_Restful.LibraryManagementSubDomain.presentationlayer.LibraryResponseModel;
import com.champsoft.Lab01_Restful.utils.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class LibraryServiceImpl implements LibraryService {

    private final LibraryRepository libraryRepository;
    private final LibraryRequestMapper libraryRequestMapper;
    private final LibraryResponseMapper libraryResponseMapper;

    @Autowired
    public LibraryServiceImpl(LibraryRepository libraryRepository, LibraryRequestMapper libraryRequestMapper, LibraryResponseMapper libraryResponseMapper) {
        this.libraryRepository = libraryRepository;
        this.libraryRequestMapper = libraryRequestMapper;
        this.libraryResponseMapper = libraryResponseMapper;
    }

    @Override
    public List<LibraryResponseModel> getAllLibraries() {
        // Retrieve all libraries from the repository
        List<Library> libraries = libraryRepository.findAll();

        // Convert the list of Library entities to LibraryResponseModel
        return libraryResponseMapper.entityListToResponseModelList(libraries);
    }

    @Override
    public LibraryResponseModel getLibraryById(String libraryId) {
        // Call the method that accepts a String
        Library library = this.libraryRepository.findByLibraryIdentifier_LibraryId(libraryId);

        if (library == null) {
            throw new NotFoundException("Library with id: " + libraryId + " not found.");
        } else {
            return this.libraryResponseMapper.entityToResponseModel(library);
        }
    }

    @Override
    public LibraryResponseModel addLibrary(LibraryRequestModel newLibraryData) {
        // Validate the incoming data
        if (newLibraryData.getName() == null || newLibraryData.getName().isEmpty()) {
            throw new IllegalArgumentException("Library name cannot be null or empty");
        }

        // Create a new Library entity from the request model
        Library newLibrary = new Library();
        newLibrary.setName(newLibraryData.getName());
        newLibrary.setAddress(newLibraryData.getAddress());
        newLibrary.setCity(newLibraryData.getCity());
        newLibrary.setState(newLibraryData.getState());
        newLibrary.setPostalCode(newLibraryData.getPostalCode());

        // Create a new LibraryIdentifier and set it in the Library entity
        LibraryIdentifier libraryIdentifier = new LibraryIdentifier(); // This will generate a new UUID
        newLibrary.setLibraryIdentifier(libraryIdentifier); // Assuming your Library entity has a setLibraryIdentifier method

        // Save the library to the repository
        Library savedLibrary = this.libraryRepository.save(newLibrary);

        // Convert the saved library to a response model
        return this.libraryResponseMapper.entityToResponseModel(savedLibrary);
    }

    @Override
    public LibraryResponseModel updateLibrary(String libraryId, LibraryRequestModel updatedLibraryData) {
        // Find the existing library by its libraryId
        Library existingLibrary = this.libraryRepository.findByLibraryIdentifier_LibraryId(libraryId);
        if (existingLibrary == null) {
            throw new NotFoundException("Library with id: " + libraryId + " not found.");
        }

        // Update the fields of the existing library
        existingLibrary.setName(updatedLibraryData.getName());
        existingLibrary.setAddress(updatedLibraryData.getAddress());
        existingLibrary.setCity(updatedLibraryData.getCity());
        existingLibrary.setState(updatedLibraryData.getState());
        existingLibrary.setPostalCode(updatedLibraryData.getPostalCode());

        // Save the updated library to the repository
        Library savedLibrary = this.libraryRepository.save(existingLibrary);

        // Convert the saved library to a response model
        return this.libraryResponseMapper.entityToResponseModel(savedLibrary);
    }

    @Override
    public String deleteLibraryById(String libraryId) {
        // Create a LibraryIdentifier from the libraryId
        LibraryIdentifier libraryIdentifier = new LibraryIdentifier(libraryId);

        // Search for the library
        Library foundLibrary = this.libraryRepository.findByLibraryIdentifier_LibraryId(libraryId);

        if (foundLibrary == null) {
            return "Library with id: " + libraryId + " not found.";
        } else {
            this.libraryRepository.delete(foundLibrary);
            return "Library with id: " + libraryId + " deleted successfully.";
        }
    }
}
