package com.dailycode.bankapp;

import com.dailycode.bankapp.model.Employee;
import com.dailycode.bankapp.repository.EmployeeRepository;
import com.dailycode.bankapp.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@SpringBootTest
class BankApplicationTests {

    @InjectMocks
    private EmployeeService service;

    @Mock
    private EmployeeRepository repository;

    @Test
    public void getUsersTest() {
        when(repository.findAll()).thenReturn(Stream.of(getEmployee()).collect(Collectors.toList()));
        assertEquals(1, service.getEmployees().size());
    }


    @Test
    public void saveUserTest() {
        Employee employee = getEmployee();
        when(repository.save(employee)).thenReturn(employee);
        assertEquals(employee, service.addEmployee(employee));
    }

    private Employee getEmployee() {
        return getEmployee(null);
    }

    private Employee getEmployee(String id) {
        return new Employee(id, "admin", "admin123", "admin@gmail.com", "Admin");
    }

    @Test
    public void deleteUserTest() {
        Employee employee = getEmployee();
        service.deleteEmployee(employee.getId());
        verify(repository, times(1)).delete(employee);
    }

}
