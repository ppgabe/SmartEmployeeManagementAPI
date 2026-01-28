package dev.ailuruslabs.employeemanagementapi.employees;

import dev.ailuruslabs.employeemanagementapi.employees.dto.request.AdminCreateRequest;
import dev.ailuruslabs.employeemanagementapi.employees.dto.request.AdminUpdateEmployeeRequest;
import dev.ailuruslabs.employeemanagementapi.employees.dto.request.PublicEmployeeCreateRequest;
import dev.ailuruslabs.employeemanagementapi.employees.dto.request.PublicEmployeeUpdateRequest;
import dev.ailuruslabs.employeemanagementapi.employees.dto.response.EmployeeResponse;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping()
    public Page<EmployeeResponse> getAllEmployees(@RequestParam(defaultValue = "0") int page,
                                                  @RequestParam(defaultValue = "10") int size) {
        return employeeService.getAllEmployees(page, size);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public EmployeeResponse findEmployeeById(@PathVariable long id) {
        return employeeService.getEmployeeById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/admin")
    public EmployeeResponse createAdminEmployee(@Valid @RequestBody AdminCreateRequest adminCreateRequest) {
        return employeeService.createEmployee(adminCreateRequest);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/admin/bulk")
    public List<EmployeeResponse> bulkCreateAdminEmployees(@Valid @RequestBody List<AdminCreateRequest> adminCreateRequests) {
        return employeeService.bulkCreateEmployees(adminCreateRequests);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/admin/update/{id}")
    public EmployeeResponse updateAdminEmployee(@PathVariable long id, @Valid @RequestBody AdminUpdateEmployeeRequest updatingRequest) {
        return employeeService.updateEmployee(id, updatingRequest);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/employee/{id}")
    public void deleteEmployee(@PathVariable long id) {
        employeeService.deleteEmployee(id);
    }

    //Public Endpoints
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/public")
    public EmployeeResponse createPublicEmployee(@Valid @RequestBody PublicEmployeeCreateRequest publicCreateRequest) {
        return employeeService.createEmployee(publicCreateRequest);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/public/update/{id}")
    public EmployeeResponse updatePublicEmployee(@PathVariable long id, @Valid @RequestBody PublicEmployeeUpdateRequest publicUpdateRequest) {
        return employeeService.updateEmployee(id, publicUpdateRequest);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/public/bulk")
    public List<EmployeeResponse> bulkCreatePublicEmployees(@Valid @RequestBody List<PublicEmployeeCreateRequest> publicCreateRequests) {
        return employeeService.bulkCreateEmployees(publicCreateRequests);
    }
}
