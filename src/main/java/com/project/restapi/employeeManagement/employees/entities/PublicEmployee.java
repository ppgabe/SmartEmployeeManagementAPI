package com.project.restapi.employeeManagement.employees.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue(Employee.PUBLIC_DISCRIMINATOR)
public class PublicEmployee extends Employee {

    public PublicEmployee() {}
    public PublicEmployee(String name, String email, int age, String position) {
        super(name, email, age, position);
    }

}
