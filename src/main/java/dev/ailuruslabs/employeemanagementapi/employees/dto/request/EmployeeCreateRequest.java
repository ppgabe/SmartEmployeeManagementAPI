package dev.ailuruslabs.employeemanagementapi.employees.dto.request;

import dev.ailuruslabs.employeemanagementapi.employees.entities.Employee;

public sealed interface EmployeeCreateRequest permits AdminCreateRequest, PublicEmployeeCreateRequest {
    String name();
    String email();
    String position();
    int age();

    Employee toEntity();
}
