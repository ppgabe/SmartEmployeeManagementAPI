package com.project.restapi.employeeManagement.dto.request;

sealed interface EmployeeCreateRequest permits AdminCreateRequest, PublicEmployeeCreateRequest {
    String name();
    String email();
    String position();
    int age();
}
