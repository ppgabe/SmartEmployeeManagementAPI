package com.project.restapi.employeeManagement.dto.response;

public sealed interface EmployeeResponse permits AdminEmployeeResponse, PublicEmployeeResponse {
    long id();

    String name();

    String email();

    int age();

    String position();
}
