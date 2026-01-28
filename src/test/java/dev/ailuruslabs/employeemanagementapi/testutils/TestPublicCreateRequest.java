package dev.ailuruslabs.employeemanagementapi.testutils;

public record TestPublicCreateRequest(
    String name,
    Integer age,
    String email,
    String position
) {}
