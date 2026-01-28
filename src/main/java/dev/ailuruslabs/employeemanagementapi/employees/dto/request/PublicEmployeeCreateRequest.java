package dev.ailuruslabs.employeemanagementapi.employees.dto.request;

import dev.ailuruslabs.employeemanagementapi.employees.entities.PublicEmployee;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record PublicEmployeeCreateRequest(
    @NotBlank(message = "Name must not be blank")
    String name,

    @Min(value = 18, message = "Age must be greater or equal to 18")
    int age,

    @Email(message = "Email must be valid")
    @NotBlank(message = "Email must not be blank")
    String email,

    @NotBlank(message = "Position must not be blank")
    String position
) implements EmployeeCreateRequest {

    @Override
    public PublicEmployee toEntity() {
        return new PublicEmployee(name, email, age, position);
    }
}
