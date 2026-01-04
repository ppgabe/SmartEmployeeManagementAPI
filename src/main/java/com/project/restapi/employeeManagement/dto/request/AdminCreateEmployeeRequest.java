package com.project.restapi.employeeManagement.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class AdminCreateEmployeeRequest {
    @NotBlank(message = "Name is required!")
    private String name;

    private int age;

    @NotBlank(message = "Position is required!")
    private String position;

    private Double salary;

    @Email(message = "Email must be valid!")
    @NotBlank(message = "Email is required!")
    private String email;

    private boolean isActive;

    public boolean active() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
