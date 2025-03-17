package com.champsoft.Lab01_Restful.employeemanagementsubdomain.dataaccesslayer;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "employees")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column (name="employee_id")
    private String employeeId;
    @Column (name="first_name")
    private String firstName;
    @Column (name="last_name")
    private String lastName;
    @Column (name="dob")
    private LocalDate dob;
    @Column (name="email", unique=true)
    private String email;
    @Column (name="title")
    private String title;
    @Column (name="salary")
    private Double salary;






}

