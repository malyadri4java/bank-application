package com.dailycode.bankapp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonIgnoreProperties (value = {"password"})
public class Employee {
    @Id
    private String id;
    private String username;
    private String password;
    private String email;
    private String role;

    public Employee clone() {
        return new Employee(null, username, password, email, role);
    }
}
