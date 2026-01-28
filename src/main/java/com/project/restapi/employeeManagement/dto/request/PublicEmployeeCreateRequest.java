package com.project.restapi.employeeManagement.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record PublicEmployeeCreateRequest(
    @NotBlank(message = "Name is required!")
    String name,

    @Min(value = 18)
    int age,

    @Email(message = "Email must be valid!")
    @NotBlank(message = "Email is required!")
    String email,

    @NotBlank(message = "Position is required! ")
    String position
) implements EmployeeCreateRequest {}
