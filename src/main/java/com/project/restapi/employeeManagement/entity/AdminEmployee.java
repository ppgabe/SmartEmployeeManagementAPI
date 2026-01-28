package com.project.restapi.employeeManagement.entity;

import com.project.restapi.employeeManagement.exceptions.InvalidSalaryException;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue(Employee.ADMIN_DISCRIMINATOR)
public class AdminEmployee extends Employee {
    private double salary;

    private boolean isActive;

    public AdminEmployee() {}
    public AdminEmployee(String name, String email, int age, String position, double salary, boolean isActive) {
        super(name, email, age, position);
        this.salary = salary;
        this.isActive = isActive;
    }

    @Override
    public void setAge(int age) {
        super.setAge(age);

        if (age > 60) {
            this.isActive = false;
        }
    }
    public double getSalary() {
        return salary;
    }
    public void setSalary(double salary) {
        this.salary = salary;

        if (salary <= 0) {
            throw new InvalidSalaryException("Salary must be above 0.");
        }
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }


}
