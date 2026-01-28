package com.project.restapi.employeeManagement.employees.dto.request;

import com.project.restapi.employeeManagement.employees.entities.Employee;

public sealed interface EmployeeUpdateRequest permits AdminUpdateEmployeeRequest, PublicEmployeeUpdateRequest {
    String name();
    String email();
    String position();
    int age();

    Employee toEntity();
}
