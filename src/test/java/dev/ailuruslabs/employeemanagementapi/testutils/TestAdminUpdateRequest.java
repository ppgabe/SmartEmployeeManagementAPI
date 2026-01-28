package dev.ailuruslabs.employeemanagementapi.testutils;

public record TestAdminUpdateRequest(
    String name,
    int age,
    String position,
    String email,
    double salary,
    boolean isActive
) {
}
