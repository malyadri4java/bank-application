package com.dailycode.bankapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transfer {
    @Id
    public String id;
    public Long fromAccNumber;
    public Long toAccNumber;
    public Double amount;
}
