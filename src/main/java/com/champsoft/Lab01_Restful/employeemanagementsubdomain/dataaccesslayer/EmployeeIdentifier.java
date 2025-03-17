package com.champsoft.Lab01_Restful.employeemanagementsubdomain.dataaccesslayer;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Embeddable  // Marks it as a value object to be embedded in an entity
@Getter
@Setter
@NoArgsConstructor
public class EmployeeIdentifier {
    @Column(name="employee_id", nullable=false, unique=true)
    private String employeeId;

    public EmployeeIdentifier(String employeeId) {
        this.employeeId = (employeeId != null && !employeeId.isEmpty()) ? employeeId : UUID.randomUUID().toString();
    }
}
