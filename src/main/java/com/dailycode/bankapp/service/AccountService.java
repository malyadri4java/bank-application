package com.dailycode.bankapp.service;

import com.dailycode.bankapp.model.Account;
import com.dailycode.bankapp.model.Transfer;
import com.dailycode.bankapp.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Service
public class AccountService {

    @Autowired
    private AccountRepository repository;

    public Account add(Account req) {
        Account newCustomer = repository.save(req);
        return newCustomer;
    }

    public String delete(String id) {
        repository.deleteById(id);
        return "Deleted customer with Id :" + id;
    }

    public List<Account> getAll() {
        return repository.findAll();
    }

    public Account getByNumber(Long accNumber) {
        return repository.findByAccNumber(accNumber);
    }

    public Account get(String customerName) {
        return repository.findByCustomerId(customerName);
    }

    public Double getBalance(Long accNumber) {
        return repository.findByAccNumber(accNumber).getBalance();
    }

    @Transactional
    public List<Account> transfer(Transfer transfer) {
        Account fromAccount = repository.findByAccNumber(transfer.getFromAccNumber());
        double tValue = fromAccount.getBalance() - transfer.getAmount();
        fromAccount.setBalance(tValue);
        fromAccount = repository.save(fromAccount);
        Account toAccount = repository.findByAccNumber(transfer.getToAccNumber());
        double toValue = toAccount.getBalance() + transfer.getAmount();
        toAccount.setBalance(toValue);
        toAccount = repository.save(toAccount);
        return Arrays.asList(fromAccount, toAccount);
    }
}
