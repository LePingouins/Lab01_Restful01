package com.champsoft.Lab01_Restful.StaffManagementSubDomain.businesslogiclayer;

import com.champsoft.Lab01_Restful.StaffManagementSubDomain.businesslogiclayer.LibrarianService;
import com.champsoft.Lab01_Restful.StaffManagementSubDomain.dataaccesslayer.Librarian;
import com.champsoft.Lab01_Restful.StaffManagementSubDomain.dataaccesslayer.LibrarianIdentifier;
import com.champsoft.Lab01_Restful.StaffManagementSubDomain.dataaccesslayer.LibrarianRepository;
import com.champsoft.Lab01_Restful.StaffManagementSubDomain.datamapperlayer.LibrarianRequestMapper;
import com.champsoft.Lab01_Restful.StaffManagementSubDomain.datamapperlayer.LibrarianResponseMapper;
import com.champsoft.Lab01_Restful.StaffManagementSubDomain.presentationlayer.LibrarianRequestModel;
import com.champsoft.Lab01_Restful.StaffManagementSubDomain.presentationlayer.LibrarianResponseModel;
import com.champsoft.Lab01_Restful.utils.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class LibrarianServiceImpl implements LibrarianService {
    private final LibrarianRepository librarianRepository;
    private final LibrarianRequestMapper librarianRequestMapper;
    private final LibrarianResponseMapper librarianResponseMapper;

    @Autowired
    public LibrarianServiceImpl(LibrarianRepository librarianRepository, LibrarianRequestMapper librarianRequestMapper, LibrarianResponseMapper librarianResponseMapper) {
        this.librarianRepository = librarianRepository;
        this.librarianRequestMapper = librarianRequestMapper;
        this.librarianResponseMapper = librarianResponseMapper;
    }

    @Override
    public List<LibrarianResponseModel> getAllLibrarians() {
        List<Librarian> librarians = librarianRepository.findAll();
        return librarianResponseMapper.entityListToResponseModelList(librarians);
    }

    @Override
    public LibrarianResponseModel getLibrarianById(String librarianId) {
        LibrarianIdentifier librarianIdentifier = new LibrarianIdentifier(librarianId);
        Librarian librarian = this.librarianRepository.findLibrarianByLibrarianIdentifier(librarianIdentifier);
        if (librarian == null) {
            throw new NotFoundException("Librarian with id: " + librarianId + " not found.");
        }
        return this.librarianResponseMapper.entityToResponseModel(librarian);
    }

    @Override
    public LibrarianResponseModel addLibrarian(LibrarianRequestModel newLibrarianData) {
        String librarianId = (newLibrarianData.getLibrarianId() == null || newLibrarianData.getLibrarianId().isEmpty())
                ? UUID.randomUUID().toString()
                : newLibrarianData.getLibrarianId();

        LibrarianIdentifier librarianIdentifier = new LibrarianIdentifier(librarianId);
        Librarian foundLibrarian = this.librarianRepository.findLibrarianByLibrarianIdentifier(librarianIdentifier);
        if (foundLibrarian != null) {
            throw new IllegalArgumentException("Librarian with ID " + librarianId + " already exists.");
        }

        Librarian librarian = this.librarianRequestMapper.requestModelToEntity(newLibrarianData);
        librarian.setLibrarianIdentifier(librarianIdentifier);
        Librarian savedLibrarian = this.librarianRepository.save(librarian);
        return this.librarianResponseMapper.entityToResponseModel(savedLibrarian);
    }

    @Override
    public LibrarianResponseModel updateLibrarian(String librarianId, LibrarianRequestModel newLibrarianData) {
        LibrarianIdentifier librarianIdentifier = new LibrarianIdentifier(librarianId);
        Librarian foundLibrarian = this.librarianRepository.findLibrarianByLibrarianIdentifier(librarianIdentifier);
        if (foundLibrarian == null) {
            throw new NotFoundException("Librarian with id: " + librarianId + " not found.");
        }

        Librarian librarian = this.librarianRequestMapper.requestModelToEntity(newLibrarianData);
        librarian.setLibrarianIdentifier(librarianIdentifier);
        librarian.setId(foundLibrarian.getId());

        Librarian savedLibrarian = this.librarianRepository.save(librarian);
        return this.librarianResponseMapper.entityToResponseModel(savedLibrarian);
    }

    @Override
    public String deleteLibrarianById(String librarianId) {
        LibrarianIdentifier librarianIdentifier = new LibrarianIdentifier(librarianId);
        Librarian foundLibrarian = this.librarianRepository.findLibrarianByLibrarianIdentifier(librarianIdentifier);
        if (foundLibrarian == null) {
            return "Librarian with id: " + librarianId + " not found.";
        }
        this.librarianRepository.delete(foundLibrarian);
        return "Librarian with id: " + librarianId + " deleted successfully.";
    }
}
