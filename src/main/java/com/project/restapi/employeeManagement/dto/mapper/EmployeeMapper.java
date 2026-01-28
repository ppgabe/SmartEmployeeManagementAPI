package com.project.restapi.employeeManagement.dto.mapper;

import com.project.restapi.employeeManagement.dto.request.AdminCreateRequest;
import com.project.restapi.employeeManagement.dto.request.AdminUpdateEmployeeRequest;
import com.project.restapi.employeeManagement.dto.request.PublicEmployeeCreateRequest;
import com.project.restapi.employeeManagement.dto.request.PublicEmployeeUpdateRequest;
import com.project.restapi.employeeManagement.dto.response.AdminEmployeeResponse;
import com.project.restapi.employeeManagement.dto.response.PublicEmployeeResponse;
import com.project.restapi.employeeManagement.entity.Employee;

import java.util.List;

public class EmployeeMapper {

    //@ADMIN
    public static Employee AdminFromCreateRequest(AdminCreateRequest req) {
         return new Employee()
                .setName(req.name())
                .setEmail(req.email())
                .setAge(req.age())
                .setSalary(req.salary())
                .setIfActive(req.isActive())
                .setPosition(req.position());
    }

    public static List<Employee> AdminFromCreateRequestList(List<AdminCreateRequest> req) {
        return req.stream()
                .map(EmployeeMapper::AdminFromCreateRequest)
                .toList();
    }

    public static Employee AdminFromUpdateRequest(Employee emp, AdminUpdateEmployeeRequest req) {
        emp.setSalary(req.salary())
                .setName(req.name())
                .setEmail(req.email())
                .setAge(req.age())
                .setPosition(req.position())
                .setIfActive(req.isActive());
        return emp;
    }

    public static AdminEmployeeResponse AdminToResponse(Employee emp) {
        return new AdminEmployeeResponse()
                .setId(emp.getId())
                .setName(emp.getName())
                .setEmail(emp.getEmail())
                .setSalary(emp.getSalary())
                .setAge(emp.getAge())
                .setActive(emp.isActive())
                .setPosition(emp.getPosition());
    }


    //@PUBLIC
    public static Employee publicFromCreateRequest(PublicEmployeeCreateRequest publicCreateRequest) {
        return new Employee()
                .setName(publicCreateRequest.name())
                .setEmail(publicCreateRequest.email())
                .setAge(publicCreateRequest.age())
                .setPosition(publicCreateRequest.position());
    }

    public static Employee publicFromUpdateRequest(Employee emp, PublicEmployeeUpdateRequest req) {
        return emp
                .setName(req.getName())
                .setEmail(req.getEmail())
                .setAge(req.getAge())
                .setPosition(req.getPosition());
    }

    public static PublicEmployeeResponse publicToResponse(Employee emp) {
        return new PublicEmployeeResponse()
                .setId(emp.getId())
                .setName(emp.getName())
                .setEmail(emp.getEmail())
                .setAge(emp.getAge())
                .setPosition(emp.getPosition());

    }

}
