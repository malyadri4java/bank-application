package com.dailycode.bankapp.service;

import com.dailycode.bankapp.model.Customer;
import com.dailycode.bankapp.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository repository;

    public Customer addCustomer(Customer customer) {
        Customer newCustomer = repository.save(customer);
        return newCustomer;
    }

    public String deleteCustomer(String id) {
        repository.deleteById(id);
        return "Deleted customer with Id :" + id;
    }

    public List<Customer> getCustomers() {
        return repository.findAll();
    }

    public Customer getCustomer(String customerName) {
        return repository.findByUsername(customerName);
    }

}
