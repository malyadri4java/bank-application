package com.dailycode.bankapp.controller;

import com.dailycode.bankapp.exception.CustomerException;
import com.dailycode.bankapp.model.Employee;
import com.dailycode.bankapp.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequestMapping (value = "/admin")
public class EmployeeController {

    @Autowired
    private EmployeeService service;

    @PostMapping ("/add")
    public Employee addEmployee(@RequestBody Employee employee) {
        Employee emp = service.getEmployee(employee.getUsername());
        if (emp != null) {
            throw new CustomerException("User already exist.");
        }
        return service.addEmployee(employee);
    }

    @DeleteMapping ("/delete/{employeeId}")
    public String deleteEmployee(@PathVariable String employeeId) {
        service.deleteEmployee(employeeId);
        return "Deleted employee record successfully!";
    }

    @GetMapping ("/list")
    public List<Employee> getEmployees() {
        return service.getEmployees();
    }
}
