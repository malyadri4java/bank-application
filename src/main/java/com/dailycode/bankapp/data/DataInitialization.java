package com.dailycode.bankapp.data;

import com.dailycode.bankapp.model.Account;
import com.dailycode.bankapp.model.Customer;
import com.dailycode.bankapp.model.Employee;
import com.dailycode.bankapp.service.AccountService;
import com.dailycode.bankapp.service.CustomerService;
import com.dailycode.bankapp.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DataInitialization {
    @Autowired
    private EmployeeService service;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private AccountService accountService;

    @PostConstruct
    public void dataInitialization() {
        //service.addEmployee(new Employee(null, "admin", "admin123", "admin@gmail.com", "Admin"));
        //customerService.addCustomer(new Customer(null, "admin", "lastname", "firstname", "mail@gmail.com"));
        //accountService.add(new Account(null, "saving", 12345L, 100.00, null));
    }
}
