package com.project.restapi.employeeManagement.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record AdminUpdateEmployeeRequest(
    @NotBlank(message = "Name must not be blank")
    String name,

    @Min(value = 18, message = "Age must be greater or equal to 18")
    int age,

    @NotBlank(message = "Position must not be blank")
    String position,

    @Email(message = "Email must be valid")
    @NotBlank(message = "Email must not be blank")
    String email,

    @DecimalMin(inclusive = false, value = "0.0")
    double salary,

    boolean isActive
) {
}
