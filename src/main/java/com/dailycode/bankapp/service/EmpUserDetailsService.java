package com.dailycode.bankapp.service;

import com.dailycode.bankapp.model.Employee;
import com.dailycode.bankapp.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class EmpUserDetailsService implements UserDetailsService {
    private String roleStr = "ROLE_%s";

    @Autowired
    private EmployeeRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Employee employee = repository.findByUsername(username);
        return new org.springframework.security.core.userdetails.User(employee.getUsername(), employee.getPassword(), getAuthority(employee.getRole()));
    }

    private List<GrantedAuthority> getAuthority(String role) {
        return Arrays.asList(new SimpleGrantedAuthority(String.format(roleStr, role.toUpperCase())));
    }
}
