package com.project.restapi.employeeManagement.testutils;

public record TestPublicResponse(
    Long id,
    String name,
    String email,
    Integer age,
    String position
) {
}
