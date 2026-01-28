package com.project.restapi.employeeManagement.employees.dto.response;

public sealed interface EmployeeResponse permits AdminEmployeeResponse, PublicEmployeeResponse {
    long id();

    String name();

    String email();

    int age();

    String position();
}
