package com.dailycode.bankapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    @Id
    public String id;
    public String username;
    public String firstName;
    public String lastName;
    public String email;

}
