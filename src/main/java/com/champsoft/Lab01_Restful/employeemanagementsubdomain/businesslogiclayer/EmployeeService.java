package com.champsoft.Lab01_Restful.employeemanagementsubdomain.businesslogiclayer;



import com.champsoft.Lab01_Restful.employeemanagementsubdomain.presentationlayer.EmployeeRequestDTO;
import com.champsoft.Lab01_Restful.employeemanagementsubdomain.presentationlayer.EmployeeResponseDTO;

import java.util.List;

public interface EmployeeService {
    List<EmployeeResponseDTO> getAllEmployees();
    EmployeeResponseDTO createEmployee(EmployeeRequestDTO employeeRequestDTO);
    EmployeeResponseDTO updateEmployee(String id, EmployeeRequestDTO employeeRequestDTO);
    String deleteEmployee(String id);
    EmployeeResponseDTO getEmployeeByEmployeeId(String employeeId);

}

