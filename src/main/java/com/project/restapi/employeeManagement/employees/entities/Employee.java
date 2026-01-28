package com.project.restapi.employeeManagement.employees.entities;

import com.project.restapi.employeeManagement.employees.exceptions.InvalidAgeException;
import jakarta.persistence.*;

@Entity
@Table(name = "employees")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "role")
public abstract class Employee {

    public static final String ADMIN_DISCRIMINATOR = "ADMIN";
    public static final String PUBLIC_DISCRIMINATOR = "PUBLIC";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @Column(name = "email", unique = true)
    private String email;

    private int age;

    private String position;
    public Employee() {}

    public Employee(String name, String email, int age, String position) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.position = position;
    }

    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;

        if (age < 18) {
            throw new InvalidAgeException("Age must be above 18.");
        }
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
