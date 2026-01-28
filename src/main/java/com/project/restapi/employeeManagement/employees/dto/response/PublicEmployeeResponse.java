package com.project.restapi.employeeManagement.employees.dto.response;

import com.project.restapi.employeeManagement.employees.entities.PublicEmployee;

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
