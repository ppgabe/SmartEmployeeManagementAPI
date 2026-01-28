package dev.ailuruslabs.employeemanagementapi.employees.exceptions;

public class InvalidAgeException extends RuntimeException {
    public InvalidAgeException(String message) {
        super(message);
    }
}
