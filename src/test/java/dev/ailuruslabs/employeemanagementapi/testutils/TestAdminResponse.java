package dev.ailuruslabs.employeemanagementapi.testutils;

public record TestAdminResponse(
    Long id,
    String name,
    String email,
    Integer age,
    Double salary,
    String position,
    Boolean isActive
) {
}
