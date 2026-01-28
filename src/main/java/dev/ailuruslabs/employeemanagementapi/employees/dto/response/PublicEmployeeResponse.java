package dev.ailuruslabs.employeemanagementapi.employees.dto.response;

import dev.ailuruslabs.employeemanagementapi.employees.entities.PublicEmployee;

public record PublicEmployeeResponse(
    long id,
    String name,
    String email,
    int age,
    String position
) implements EmployeeResponse {

    public static PublicEmployeeResponse fromEntity(PublicEmployee publicEmployee) {
        return new PublicEmployeeResponse(
            publicEmployee.getId(),
            publicEmployee.getName(),
            publicEmployee.getEmail(),
            publicEmployee.getAge(),
            publicEmployee.getPosition()
        );
    }
}
