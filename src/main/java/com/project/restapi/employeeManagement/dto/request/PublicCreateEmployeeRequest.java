package com.project.restapi.employeeManagement.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class PublicCreateEmployeeRequest {
    @NotBlank(message = "Name is required!")
    private String name;

    private int age;

    @Email(message = "Email must be valid!")
    @NotBlank(message = "Email is required!")
    private String email;

    @NotBlank(message = "Position is required! ")
    private String position;

    public String getName() {
        return name;
    }

    public PublicCreateEmployeeRequest setName(String name) {
        this.name = name;
        return this;
    }

    public int getAge() {
        return age;
    }

    public PublicCreateEmployeeRequest setAge(int age) {
        this.age = age;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public PublicCreateEmployeeRequest setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPosition() {
        return position;
    }

    public PublicCreateEmployeeRequest setPosition(String position) {
        this.position = position;
        return this;
    }
}
