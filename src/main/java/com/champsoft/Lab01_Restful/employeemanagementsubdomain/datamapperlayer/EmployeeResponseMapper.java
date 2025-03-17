package com.champsoft.Lab01_Restful.employeemanagementsubdomain.datamapperlayer;


import com.champsoft.Lab01_Restful.employeemanagementsubdomain.dataaccesslayer.Employee;
import com.champsoft.Lab01_Restful.employeemanagementsubdomain.presentationlayer.EmployeeResponseDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmployeeResponseMapper {


    EmployeeResponseDTO toDTO(Employee employee);

    List<EmployeeResponseDTO> toDTOList(List<Employee> employees);

}
