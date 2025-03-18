package com.champsoft.Lab01_Restful.StaffManagementSubDomain.businesslogiclayer;

import com.champsoft.Lab01_Restful.StaffManagementSubDomain.dataaccesslayer.Librarian;
import com.champsoft.Lab01_Restful.StaffManagementSubDomain.dataaccesslayer.LibrarianRepository;
import com.champsoft.Lab01_Restful.StaffManagementSubDomain.datamapperlayer.LibrarianRequestMapper;
import com.champsoft.Lab01_Restful.StaffManagementSubDomain.datamapperlayer.LibrarianResponseMapper;
import com.champsoft.Lab01_Restful.StaffManagementSubDomain.presentationlayer.LibrarianRequestModel;
import com.champsoft.Lab01_Restful.StaffManagementSubDomain.presentationlayer.LibrarianResponseModel;
import com.champsoft.Lab01_Restful.utils.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        Librarian librarian = librarianRepository.findByLibrarianIdentifier_LibrarianId(librarianId);
        if (librarian == null) {
            throw new NotFoundException("Librarian with id: " + librarianId + " not found.");
        }
        return librarianResponseMapper.entityToResponseModel(librarian);
    }

    @Override
    public LibrarianResponseModel addLibrarian(LibrarianRequestModel newLibrarianData) {
        // Validate the incoming data
        if (newLibrarianData.getEmail() == null || newLibrarianData.getEmail().isEmpty()) {
            throw new IllegalArgumentException("Email cannot be null or empty");
        }

        Librarian newLibrarian = librarianRequestMapper.requestModelToEntity(newLibrarianData);

        Librarian savedLibrarian = librarianRepository.save(newLibrarian);

        return librarianResponseMapper.entityToResponseModel(savedLibrarian);
    }

    @Override
    public LibrarianResponseModel updateLibrarian(String librarianId, LibrarianRequestModel updatedLibrarianData) {
        Librarian existingLibrarian = librarianRepository.findByLibrarianIdentifier_LibrarianId(librarianId);
        if (existingLibrarian == null) {
            throw new NotFoundException("Librarian with id: " + librarianId + " not found.");
        }

        // Update the fields of the existing librarian
        existingLibrarian.setFirstName(updatedLibrarianData.getFirstName());
        existingLibrarian.setLastName(updatedLibrarianData.getLastName());
        existingLibrarian.setEmail(updatedLibrarianData.getEmail());
        existingLibrarian.setSalary(updatedLibrarianData.getSalary());

        Librarian savedLibrarian = librarianRepository.save(existingLibrarian);

        return librarianResponseMapper.entityToResponseModel(savedLibrarian);
    }

    @Override
    public String deleteLibrarianById(String librarianId) {
        Librarian librarian = librarianRepository.findByLibrarianIdentifier_LibrarianId(librarianId);
        if (librarian == null) {
            return "Librarian with id: " + librarianId + " not found.";
        }
        librarianRepository.delete(librarian);
        return "Librarian with id: " + librarianId + " deleted successfully.";
    }
}
