package com.project.restapi.employeeManagement.service;

import com.project.restapi.employeeManagement.dto.request.AdminCreateEmployeeRequest;
import com.project.restapi.employeeManagement.dto.request.AdminUpdateEmployeeRequest;
import com.project.restapi.employeeManagement.dto.mapper.EmployeeMapper;
import com.project.restapi.employeeManagement.dto.request.PublicCreateEmployeeRequest;
import com.project.restapi.employeeManagement.dto.request.PublicUpdateEmployeeRequest;
import com.project.restapi.employeeManagement.dto.response.AdminEmployeeResponse;
import com.project.restapi.employeeManagement.dto.response.PublicEmployeeResponse;
import com.project.restapi.employeeManagement.entity.Employee;
import com.project.restapi.employeeManagement.repository.EmployeeRepository;
import com.project.restapi.employeeManagement.exceptions.EmailAlreadyExistsException;
import com.project.restapi.employeeManagement.exceptions.ResourcesNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional
public class EmployeeService {

    private final EmployeeRepository empRepository;

    @Autowired
    public EmployeeService(EmployeeRepository empRepository) {
        this.empRepository = empRepository;
    }

    //General method to search employee for reusability
    public Employee getEmployeeById(Long id) {
        return empRepository.findEmpById(id)
                .orElseThrow(() -> new ResourcesNotFoundException("Employee not found in id: " + id));
    }

    //@Admin Methods
    public AdminEmployeeResponse createEmployee_Admin(AdminCreateEmployeeRequest createRequest) {
        if (empRepository.existsEmpByEmail(createRequest.getEmail())) {
            throw new EmailAlreadyExistsException("Email already exists.");
        }

        Employee emp = EmployeeMapper.AdminFromCreateRequest(createRequest);
        Employee saveEmp = empRepository.save(emp);
        return EmployeeMapper.AdminToResponse(saveEmp);
    }

    //Bulk
    @Transactional
    public List<AdminEmployeeResponse> createEmployee_AdminBulk(List<AdminCreateEmployeeRequest> createRequest) {
        List<Employee> employee = EmployeeMapper.AdminFromCreateRequestList(createRequest);
        List<Employee> save = empRepository.saveAll(employee);

        return save.stream()
                .map(EmployeeMapper::AdminToResponse)
                .collect(Collectors.toList());
    }

    public Page<AdminEmployeeResponse> getAllEmployees(int page, int size) {
        Pageable  pageable = PageRequest.of(page, size);
        Page<Employee> empPage = empRepository.findAll(pageable);
        return empPage.map(EmployeeMapper::AdminToResponse);
    }

    public AdminEmployeeResponse getEmployeeById_Admin(Long id) {
        Employee emp = empRepository.findEmpById(id)
                .orElseThrow(() -> new ResourcesNotFoundException("Employee not found in id: " + id));
        return EmployeeMapper.AdminToResponse(emp);
    }

    public AdminEmployeeResponse updateEmployee_Admin(Long id, AdminUpdateEmployeeRequest updateRequest) {

        Employee employee = getEmployeeById(id);

        if (!employee.getEmail().equals(updateRequest.getEmail()) &&
                empRepository.existsEmpByEmail(updateRequest.getEmail())) {
            throw new EmailAlreadyExistsException("Email already exists.");
        }

        Employee updateEmp = EmployeeMapper.AdminFromUpdateRequest(employee, updateRequest);
        Employee saveEmp = empRepository.save(updateEmp);
        return EmployeeMapper.AdminToResponse(saveEmp);
    }

    public AdminEmployeeResponse deleteEmployee(Long id) {
        Employee employee = getEmployeeById(id);

        if (employee.isActive()) {
            throw new IllegalArgumentException("Active employees  are not allowed to delete.");
        }

        empRepository.deleteById(id);
        return EmployeeMapper.AdminToResponse(employee);
    }

    //@Public Methods
    public PublicEmployeeResponse createEmployee_Public(PublicCreateEmployeeRequest publicCreateRequest) {
        if (empRepository.existsEmpByEmail(publicCreateRequest.getEmail())) {
            throw new  EmailAlreadyExistsException("Email already exists.");
        }

        Employee employee = EmployeeMapper.publicFromCreateRequest(publicCreateRequest);
        Employee saveEmp = empRepository.save(employee);

        return EmployeeMapper.publicToResponse(saveEmp);
    }

    public PublicEmployeeResponse getEmployeeById_Public(Long id) {
        Employee employee = empRepository.findEmpById(id)
                .orElseThrow(() -> new ResourcesNotFoundException("Employee not found in id: " + id));

        return EmployeeMapper.publicToResponse(employee);
    }

    public PublicEmployeeResponse updateEmployee_Public(Long id, PublicUpdateEmployeeRequest publicUpdateRequest) {
        Employee employee = getEmployeeById(id);

        if (!employee.getEmail().equals(publicUpdateRequest.getEmail())
        && empRepository.existsEmpByEmail(publicUpdateRequest.getEmail())) {
            throw new EmailAlreadyExistsException("Email already exists.");
        }

        Employee updateEmployee = EmployeeMapper.publicFromUpdateRequest(employee, publicUpdateRequest);
        Employee saveUpdatedEmployee = empRepository.save(updateEmployee);
        return EmployeeMapper.publicToResponse(saveUpdatedEmployee);
    }
}
