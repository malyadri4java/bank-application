package com.dailycode.bankapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountLink {
    @Id
    public String id;
    public Long accNumber;
    public String customerId;
}
