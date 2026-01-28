package com.project.restapi.employeeManagement.employees;

import com.project.restapi.employeeManagement.employees.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    boolean existsEmployeeByEmail(String email);

    Optional<Employee> findEmployeeById(long id);
}
