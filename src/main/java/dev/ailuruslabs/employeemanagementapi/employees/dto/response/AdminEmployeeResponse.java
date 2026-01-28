package dev.ailuruslabs.employeemanagementapi.employees.dto.response;

import dev.ailuruslabs.employeemanagementapi.employees.entities.AdminEmployee;

public record AdminEmployeeResponse(
    long id,
    String name,
    String email,
    int age,
    double salary,
    String position,
    boolean isActive
) implements EmployeeResponse {

    public static AdminEmployeeResponse fromEntity(AdminEmployee adminEmployee) {
        return new AdminEmployeeResponse(
            adminEmployee.getId(),
            adminEmployee.getName(),
            adminEmployee.getEmail(),
            adminEmployee.getAge(),
            adminEmployee.getSalary(),
            adminEmployee.getPosition(),
            adminEmployee.isActive()
        );
    }

}
