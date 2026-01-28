package dev.ailuruslabs.employeemanagementapi.employees.exceptions;

public class InvalidSalaryException extends RuntimeException {
    public InvalidSalaryException(String message) {
        super(message);
    }
}
