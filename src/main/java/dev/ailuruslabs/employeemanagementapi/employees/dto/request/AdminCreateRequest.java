package dev.ailuruslabs.employeemanagementapi.employees.dto.request;

import dev.ailuruslabs.employeemanagementapi.employees.entities.AdminEmployee;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record AdminCreateRequest(
    @NotBlank(message = "Name must not be blank")
    String name,

    @Min(value = 18, message = "Age must be greater or equal to 18") // Jakarta validation for minimum value. Errors at the edges!
    int age,

    @NotBlank(message = "Position must not be blank")
    String position,

    @DecimalMin(inclusive = false, value = "0.0")
    double salary,  // No need to use the Double wrapper class

    @Email(message = "Email must be valid")
    @NotBlank(message = "Email must not be blank")
    String email,

    boolean isActive
) implements EmployeeCreateRequest {

    @Override
    public AdminEmployee toEntity() {
        return new AdminEmployee(name, email, age, position, salary, isActive);
    }
}
