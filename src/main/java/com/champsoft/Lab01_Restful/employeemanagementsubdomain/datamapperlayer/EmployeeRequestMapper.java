package com.champsoft.Lab01_Restful.employeemanagementsubdomain.datamapperlayer;

import com.champsoft.Lab01_Restful.employeemanagementsubdomain.dataaccesslayer.Employee;
import com.champsoft.Lab01_Restful.employeemanagementsubdomain.presentationlayer.EmployeeRequestDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EmployeeRequestMapper {
    @Mapping(target = "id", ignore = true)
    Employee toEntity(EmployeeRequestDTO employeeRequestDTO);

}