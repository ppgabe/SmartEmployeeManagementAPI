package com.project.restapi.employeeManagement.dto.request;

import com.project.restapi.employeeManagement.entity.Employee;

public sealed interface EmployeeCreateRequest permits AdminCreateRequest, PublicEmployeeCreateRequest {
    String name();
    String email();
    String position();
    int age();

    Employee toEntity();
}
