package com.champsoft.Lab01_Restful.employeemanagementsubdomain.businesslogiclayer;


import com.champsoft.Lab01_Restful.employeemanagementsubdomain.dataaccesslayer.Employee;
import com.champsoft.Lab01_Restful.employeemanagementsubdomain.dataaccesslayer.EmployeeRepository;
import com.champsoft.Lab01_Restful.employeemanagementsubdomain.datamapperlayer.EmployeeRequestMapper;
import com.champsoft.Lab01_Restful.employeemanagementsubdomain.datamapperlayer.EmployeeResponseMapper;
import com.champsoft.Lab01_Restful.employeemanagementsubdomain.presentationlayer.EmployeeRequestDTO;
import com.champsoft.Lab01_Restful.employeemanagementsubdomain.presentationlayer.EmployeeResponseDTO;
import com.champsoft.Lab01_Restful.utils.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Service

public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final EmployeeResponseMapper employeeResponseMapper;
    private final EmployeeRequestMapper employeeRequestMapper;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository,
                               EmployeeResponseMapper employeeResponseMapper,
                               EmployeeRequestMapper employeeRequestMapper) {
        this.employeeRepository = employeeRepository;
        this.employeeResponseMapper = employeeResponseMapper;
        this.employeeRequestMapper = employeeRequestMapper;
    }

    @Override
    public List<EmployeeResponseDTO> getAllEmployees() {
        List<Employee> employees = this.employeeRepository.findAll();
        List<EmployeeResponseDTO> employeeResponseDTOs = new ArrayList<>();

        for (Employee employee : employees) {
            EmployeeResponseDTO responseDTO = employeeResponseMapper.toDTO(employee);

            Period period = Period.between(employee.getDob(), LocalDate.now());
            responseDTO.setAge(period.getYears());
            employeeResponseDTOs.add(responseDTO);
        }
        return employeeResponseDTOs;

    }

    @Override
    public EmployeeResponseDTO getEmployeeByEmployeeId(String employeeId) {
        Employee employee = this.employeeRepository.findByEmployeeId(employeeId);
        if (employee == null) {
            throw new NotFoundException("Employee with " + employeeId + " not found.");
        }
        return this.employeeResponseMapper.toDTO(employee);
    }

    @Override
    public EmployeeResponseDTO createEmployee(EmployeeRequestDTO employeeRequestDTO) {
            Employee employee = this.employeeRequestMapper.toEntity(employeeRequestDTO);
            Employee savedEmployee = employeeRepository.save(employee);
            EmployeeResponseDTO responseDTO = employeeResponseMapper.toDTO(savedEmployee);

        Period period = Period.between(savedEmployee.getDob(), LocalDate.now());
        responseDTO.setAge(period.getYears());


            return responseDTO;
        }


    @Override
    public EmployeeResponseDTO updateEmployee(String employeeId, EmployeeRequestDTO newEmployeeData) {
        String message = "";
        Employee employee = this.employeeRepository.findByEmployeeId(employeeId);
        if (employee != null) {
            employee.setFirstName(newEmployeeData.getFirstName());
            employee.setLastName(newEmployeeData.getLastName());
            employee.setDob(newEmployeeData.getDob());
            employee.setEmail(newEmployeeData.getEmail());
            employee.setTitle(newEmployeeData.getTitle());
            employee.setSalary(newEmployeeData.getSalary());
            Employee savedEmployee = this.employeeRepository.save(employee);

            if (savedEmployee != null) {
                message = "Employee updated successfully.";
            } else {
                message = "Could not update employee in repository.";
            }



            EmployeeResponseDTO responseDTO = employeeResponseMapper.toDTO(employee);
            Period period = Period.between(employee.getDob(), LocalDate.now());
            responseDTO.setAge(period.getYears());
            System.out.println(message);
            return responseDTO;


        }
        message = "Employee not found.";
        System.out.println(message);

        return null;

    }


    @Override
    public String deleteEmployee(String id) {

        String message = "";
        Employee foundEmployee = this.employeeRepository.findByEmployeeId(id);

        if (foundEmployee == null) {
            message = "Employee with ID: " + id + " not found in repository.";
         //   System.out.println(message);
        } else {
            this.employeeRepository.delete(foundEmployee);
            message = "Employee with ID: " + id + " deleted successfully.";
          //  System.out.println(message);
        }
        return message;
    }

}
