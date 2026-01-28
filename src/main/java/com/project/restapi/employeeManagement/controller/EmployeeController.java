package com.project.restapi.employeeManagement.controller;

import com.project.restapi.employeeManagement.dto.request.AdminCreateRequest;
import com.project.restapi.employeeManagement.dto.request.AdminUpdateEmployeeRequest;
import com.project.restapi.employeeManagement.dto.request.PublicEmployeeCreateRequest;
import com.project.restapi.employeeManagement.dto.request.PublicEmployeeUpdateRequest;
import com.project.restapi.employeeManagement.dto.response.AdminEmployeeResponse;
import com.project.restapi.employeeManagement.dto.response.PublicEmployeeResponse;
import com.project.restapi.employeeManagement.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService empService;

    @Autowired
    public EmployeeController(EmployeeService empService) {
        this.empService = empService;
    }

    //Admin Endpoints
    @PostMapping("/admin")
    public AdminEmployeeResponse createEmployee(@Valid @RequestBody AdminCreateRequest adminCreateRequest) {
        return empService.createEmployee_Admin(adminCreateRequest);
    }

    @PostMapping("/admin/bulk")
    public List<AdminEmployeeResponse> createEmployeesBulk(@Valid @RequestBody List<AdminCreateRequest> adminCreateRequests) {
        return empService.createEmployee_AdminBulk(adminCreateRequests);
    }
    @GetMapping("/admin/{id}")
    public AdminEmployeeResponse findEmpById(@PathVariable Long id) {
        return empService.getEmployeeById_Admin(id);
    }

    @GetMapping("/admin")
    public Page<AdminEmployeeResponse> getAllEmployees(@RequestParam(defaultValue = "0") int page,
                                                       @RequestParam(defaultValue = "10") int size) {
        return empService.getAllEmployees(page, size);
    }

    @PatchMapping("/admin/update/{id}")
    public AdminEmployeeResponse updateEmployee(@PathVariable Long id, @Valid @RequestBody AdminUpdateEmployeeRequest updatingRequest) {
        return empService.updateEmployee_Admin(id, updatingRequest);
    }

    @DeleteMapping("/admin/delete/{id}")
    public AdminEmployeeResponse deleteEmployee(@PathVariable Long id) {
        return empService.deleteEmployee(id);
    }

    //Public Endpoints
    @PostMapping("/public")
    public PublicEmployeeResponse createEmployee(@Valid @RequestBody PublicEmployeeCreateRequest publicCreateRequest) {
        return empService.createEmployee_Public(publicCreateRequest);
    }

    @GetMapping("/public/{id}")
    public PublicEmployeeResponse findEmployeeById(@PathVariable Long id) {
        return empService.getEmployeeById_Public(id);
    }

    @PatchMapping("/public/update/{id}")
    public PublicEmployeeResponse updateEmployee(@PathVariable Long id, @Valid @RequestBody PublicEmployeeUpdateRequest publicUpdateRequest) {
        return empService.updateEmployee_Public(id, publicUpdateRequest);
    }
}
