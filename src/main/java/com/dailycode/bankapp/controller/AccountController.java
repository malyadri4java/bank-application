package com.dailycode.bankapp.controller;

import com.dailycode.bankapp.exception.CustomerException;
import com.dailycode.bankapp.model.Account;
import com.dailycode.bankapp.model.AccountLink;
import com.dailycode.bankapp.model.Employee;
import com.dailycode.bankapp.model.Transfer;
import com.dailycode.bankapp.service.AccountService;
import com.dailycode.bankapp.util.AccountPDFStatemnt;
import com.dailycode.bankapp.util.TimeUtil;
import com.lowagie.text.DocumentException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@Slf4j
@RequestMapping (value = "/account")
@Api(value = "Account Controller", tags = {"To do account related operations."})
public class AccountController {

    @Autowired
    private AccountService service;

    @PostMapping ("/add")
    public Account add(@RequestBody Account account) {
        Account account1 = service.getByNumber(account.getAccNumber());
        if (account1 != null) {
            throw new CustomerException("Customer already exist.");
        }
        return service.add(account);
    }

    @DeleteMapping ("/delete/{accountId}")
    public String delete(@PathVariable String accountId) {
        return service.delete(accountId);
    }

    @GetMapping ("/list")
    @ApiOperation (value = "get account details", notes = " its a account details request", response = Account.class)
    public List<Account> getAll() {
        return service.getAll();
    }

    @GetMapping ("/balance/{accNumber}")
    public Double getBalance(@PathVariable long accNumber) {
        return service.getBalance(accNumber);
    }

    @PostMapping ("/transfer")
    public List<Account> transfer(@RequestBody Transfer transfer) {
        return service.transfer(transfer);
    }

    @PostMapping ("/link")
    public Account link(@RequestBody AccountLink account) {
        Account account1 = service.getByNumber(account.getAccNumber());
        account1.setCustomerId(account.getCustomerId());
        return service.add(account1);
    }

    @GetMapping (value = "/export/pdf", produces = MediaType.APPLICATION_PDF_VALUE)
    @ApiOperation (value = "Download Account details", notes = " Download Account details with all acccounts.", response = Account.class)
    public ResponseEntity<InputStreamResource> exportToPDF() throws DocumentException, IOException {
        String currentDateTime = TimeUtil.dateAndTimeAsString();
        String headerValue = "attachment; filename=users_" + currentDateTime + ".pdf";
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", headerValue);

        List<Account> accounts = service.getAll();
        AccountPDFStatemnt exporter = new AccountPDFStatemnt(accounts);
        InputStreamResource inputStreamResource = new InputStreamResource(exporter.export());

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(inputStreamResource);
    }
}
