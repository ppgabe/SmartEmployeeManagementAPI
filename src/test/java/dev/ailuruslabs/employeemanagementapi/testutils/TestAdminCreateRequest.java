package dev.ailuruslabs.employeemanagementapi.testutils;

public record TestAdminCreateRequest(
    String name,
    int age,
    String position,
    double salary,
    String email,
    boolean isActive
) {}