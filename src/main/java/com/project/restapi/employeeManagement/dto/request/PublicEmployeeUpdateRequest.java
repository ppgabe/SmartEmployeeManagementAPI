package com.project.restapi.employeeManagement.dto.request;

import com.project.restapi.employeeManagement.entity.Employee;
import com.project.restapi.employeeManagement.entity.PublicEmployee;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record PublicEmployeeUpdateRequest(
    @NotBlank(message = "Name must not be blank")
    String name,

    @Min(value = 18, message = "Age must be greater or equal to 18")
    int age,

    @Email(message = "Email must be valid")
    @NotBlank(message = "Email must not be blank")
    String email,

    @NotBlank(message = "Position must not be blank")
    String position
) implements EmployeeUpdateRequest {
    @Override
    public PublicEmployee toEntity() {
        return new PublicEmployee(name, email, age, position);
    }
}
