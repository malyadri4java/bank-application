package com.dailycode.bankapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    @Id
    public String id;
    public String type;
    public Long accNumber;
    public double balance;
    public String customerId;
}
