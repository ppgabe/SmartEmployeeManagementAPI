package com.project.restapi.employeeManagement.employees.dto.request;

import com.project.restapi.employeeManagement.employees.entities.Employee;

public sealed interface EmployeeCreateRequest permits AdminCreateRequest, PublicEmployeeCreateRequest {
    String name();
    String email();
    String position();
    int age();

    Employee toEntity();
}
