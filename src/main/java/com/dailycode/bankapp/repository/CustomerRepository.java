package com.dailycode.bankapp.repository;

import com.dailycode.bankapp.model.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends MongoRepository<Customer, String> {

    public Customer findByFirstName(String firstName);
    public Customer findByUsername(String firstName);

    public List<Customer> findByLastName(String lastName);

}
