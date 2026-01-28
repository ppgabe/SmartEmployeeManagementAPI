package com.project.restapi.employeeManagement.testutils;

public record TestAdminCreateRequest(
    String name,
    int age,
    String position,
    double salary,
    String email,
    boolean isActive
) {}