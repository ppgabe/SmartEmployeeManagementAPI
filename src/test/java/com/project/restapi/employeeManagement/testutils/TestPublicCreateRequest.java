package com.project.restapi.employeeManagement.testutils;

public record TestPublicCreateRequest(
    String name,
    Integer age,
    String email,
    String position
) {}
