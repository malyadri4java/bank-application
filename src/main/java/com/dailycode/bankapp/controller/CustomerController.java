package com.dailycode.bankapp.controller;

import com.dailycode.bankapp.exception.CustomerException;
import com.dailycode.bankapp.model.Customer;
import com.dailycode.bankapp.service.CustomerService;
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
@RequestMapping (value = "/customer")
public class CustomerController {

    @Autowired
    private CustomerService service;

    @PostMapping ("/add")
    public Customer addCustomer(@RequestBody Customer customer) {
        Customer cust = service.getCustomer(customer.getUsername());
        if (cust != null) {
            throw new CustomerException("Customer already exist.");
        }
        return service.addCustomer(customer);
    }

    @DeleteMapping ("/delete/{custId}")
    public String deleteCustomer(@PathVariable String custId) {
        return service.deleteCustomer(custId);
    }

    @GetMapping ("/list")
    public List<Customer> getCustomers() {
        return service.getCustomers();
    }
}
