package com.dailycode.bankapp.service;

import com.dailycode.bankapp.model.Employee;
import com.dailycode.bankapp.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository repository;

    //@Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public Employee addEmployee(Employee employee) {
        Employee emp = employee.clone();
        //emp.setPassword(passwordEncoder.encode(employee.getPassword()));
        return repository.save(emp);
    }

    public void deleteEmployee(String employeeId) {
        repository.deleteById(employeeId);
    }

    public List<Employee> getEmployees() {
        return repository.findAll(Sort.by(Sort.Order.asc("username")));
    }

    public Employee getEmployee(String username) {
        return repository.findByUsername(username);
    }
}
