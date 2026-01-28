package com.project.restapi.employeeManagement.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record AdminCreateRequest(
    @NotBlank(message = "Name is required!")
    String name,

    @Min(value = 18) // Jakarta validation for minimum value. Errors at the edges!
    int age,

    @NotBlank(message = "Position is required!")
    String position,

    @DecimalMin(inclusive = false, value = "0.0")
    double salary,  // No need to use the Double wrapper class

    @Email(message = "Email must be valid!")
    @NotBlank(message = "Email is required!")
    String email,

    boolean isActive
) implements EmployeeCreateRequest {}
