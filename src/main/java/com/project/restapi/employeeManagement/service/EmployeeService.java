package com.project.restapi.employeeManagement.service;

import com.project.restapi.employeeManagement.dto.request.*;
import com.project.restapi.employeeManagement.dto.response.AdminEmployeeResponse;
import com.project.restapi.employeeManagement.dto.response.EmployeeResponse;
import com.project.restapi.employeeManagement.dto.response.PublicEmployeeResponse;
import com.project.restapi.employeeManagement.entity.AdminEmployee;
import com.project.restapi.employeeManagement.entity.Employee;
import com.project.restapi.employeeManagement.entity.PublicEmployee;
import com.project.restapi.employeeManagement.repository.EmployeeRepository;
import com.project.restapi.employeeManagement.exceptions.EmailAlreadyExistsException;
import com.project.restapi.employeeManagement.exceptions.ResourcesNotFoundException;
import org.hibernate.Hibernate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class EmployeeService {

    // You would place @Autowired here, however...
    private final EmployeeRepository employeeRepository;

    // Constructor injection is the preferred method for DI now.
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public EmployeeResponse getEmployeeById(long id) {
        return createEmployeeResponse(getEmployeeEntityById(id));
    }

    private Employee getEmployeeEntityById(long id) {
        return employeeRepository.findEmployeeById(id)
            .orElseThrow(() -> new ResourcesNotFoundException("Employee not found in id: " + id));
    }

    private EmployeeResponse createEmployeeResponse(Employee employee) {
        Employee unproxiedEmployee = (Employee) Hibernate.unproxy(employee);

        return switch (unproxiedEmployee) {
            case AdminEmployee acr -> AdminEmployeeResponse.fromEntity(acr);
            case PublicEmployee pcr -> PublicEmployeeResponse.fromEntity(pcr);
            case null, default -> throw new IllegalStateException("Employee does not have an implemented Response DTO.");
        };
    }

    public EmployeeResponse createEmployee(EmployeeCreateRequest createRequest) {
        if (employeeRepository.existsEmployeeByEmail(createRequest.email())) {
            throw new EmailAlreadyExistsException("Email already exists");
        }

        Employee employee = employeeRepository.save(createRequest.toEntity());

        return createEmployeeResponse(employee);
    }

    //Bulk
    @Transactional
    public List<EmployeeResponse> bulkCreateEmployees(List<? extends EmployeeCreateRequest> createRequests) {
        createRequests.forEach(req -> {
            if (employeeRepository.existsEmployeeByEmail(req.email())) {
                throw new EmailAlreadyExistsException("Email already exists");
            }
        });

        return createRequests.stream()
            .map(EmployeeCreateRequest::toEntity)
            .map(employeeRepository::save)
            .map(this::createEmployeeResponse)
            .toList();
    }

    public Page<EmployeeResponse> getAllEmployees(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Employee> employeePage = employeeRepository.findAll(pageable);
        return employeePage.map(this::createEmployeeResponse);
    }

    public void deleteEmployee(long id) {
        Employee employeeToDelete = getEmployeeEntityById(id);

        if (employeeToDelete instanceof AdminEmployee admin && admin.isActive()) {
            throw new IllegalArgumentException("Active employees cannot be deleted.");
        }

        employeeRepository.deleteById(id);
    }

    public EmployeeResponse updateEmployee(long id, EmployeeUpdateRequest updateRequest) {

        Employee employee = getEmployeeEntityById(id);

        if (!employee.getEmail().equals(updateRequest.email()) &&
            employeeRepository.existsEmployeeByEmail(updateRequest.email())) {
            throw new EmailAlreadyExistsException("Email already exists.");
        }

        Employee updatedEmployee = employeeRepository.save(updateRequest.toEntity());

        return createEmployeeResponse(updatedEmployee);
    }
}


