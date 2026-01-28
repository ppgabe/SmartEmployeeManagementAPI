package com.project.restapi.employeeManagement.dto.request;

import com.project.restapi.employeeManagement.entity.Employee;

public sealed interface EmployeeUpdateRequest permits AdminUpdateEmployeeRequest, PublicEmployeeUpdateRequest {
    String name();
    String email();
    String position();
    int age();

    Employee toEntity();
}
