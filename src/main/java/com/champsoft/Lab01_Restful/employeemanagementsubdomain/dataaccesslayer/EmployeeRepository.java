package com.champsoft.Lab01_Restful.employeemanagementsubdomain.dataaccesslayer;


import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Employee findByEmployeeId(String employeeId);
}
