package com.dailycode.bankapp.repository;

import com.dailycode.bankapp.model.Account;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends MongoRepository<Account, String> {
    Account findByCustomerId(String customerName);

    Account findByAccNumber(Long accNumber);
}
