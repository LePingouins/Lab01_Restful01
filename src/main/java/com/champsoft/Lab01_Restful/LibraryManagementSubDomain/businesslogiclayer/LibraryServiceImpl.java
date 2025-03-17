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
import java.util.UUID;

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
        List<Library> libraries = libraryRepository.findAll();
        return libraryResponseMapper.entityListToResponseModelList(libraries);
    }

    @Override
    public LibraryResponseModel getLibraryById(String libraryId) {
        LibraryIdentifier libraryIdentifier = new LibraryIdentifier(libraryId);
        Library library = this.libraryRepository.findLibraryByLibraryIdentifier(libraryIdentifier);
        if (library == null) {
            throw new NotFoundException("Library with id: " + libraryId + " not found.");
        }
        return this.libraryResponseMapper.entityToResponseModel(library);
    }

    @Override
    public LibraryResponseModel addLibrary(LibraryRequestModel newLibraryData) {
        String libraryId = (newLibraryData.getLibraryId() == null || newLibraryData.getLibraryId().isEmpty())
                ? UUID.randomUUID().toString()
                : newLibraryData.getLibraryId();

        LibraryIdentifier libraryIdentifier = new LibraryIdentifier(libraryId);
        Library foundLibrary = this.libraryRepository.findLibraryByLibraryIdentifier(libraryIdentifier);
        if (foundLibrary != null) {
            throw new IllegalArgumentException("Library with ID " + libraryId + " already exists.");
        }

        Library library = this.libraryRequestMapper.requestModelToEntity(newLibraryData);
        library.setLibraryIdentifier(libraryIdentifier);
        Library savedLibrary = this.libraryRepository.save(library);
        return this.libraryResponseMapper.entityToResponseModel(savedLibrary);
    }

    @Override
    public LibraryResponseModel updateLibrary(String libraryId, LibraryRequestModel newLibraryData) {
        LibraryIdentifier libraryIdentifier = new LibraryIdentifier(libraryId);
        Library foundLibrary = this.libraryRepository.findLibraryByLibraryIdentifier(libraryIdentifier);
        if (foundLibrary == null) {
            throw new NotFoundException("Library with id: " + libraryId + " not found.");
        }

        Library library = this.libraryRequestMapper.requestModelToEntity(newLibraryData);
        library.setLibraryIdentifier(libraryIdentifier);
        library.setId(foundLibrary.getId());

        Library savedLibrary = this.libraryRepository.save(library);
        return this.libraryResponseMapper.entityToResponseModel(savedLibrary);
    }

    @Override
    public String deleteLibraryById(String libraryId) {
        LibraryIdentifier libraryIdentifier = new LibraryIdentifier(libraryId);
        Library foundLibrary = this.libraryRepository.findLibraryByLibraryIdentifier(libraryIdentifier);
        if (foundLibrary == null) {
            return "Library with id: " + libraryId + " not found.";
        }
        this.libraryRepository.delete(foundLibrary);
        return "Library with id: " + libraryId + " deleted successfully.";
    }
}
