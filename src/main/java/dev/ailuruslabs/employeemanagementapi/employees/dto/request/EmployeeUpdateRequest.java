package dev.ailuruslabs.employeemanagementapi.employees.dto.request;

import dev.ailuruslabs.employeemanagementapi.employees.entities.Employee;

public sealed interface EmployeeUpdateRequest permits AdminUpdateEmployeeRequest, PublicEmployeeUpdateRequest {
    String name();
    String email();
    String position();
    int age();

    Employee toEntity();
}
