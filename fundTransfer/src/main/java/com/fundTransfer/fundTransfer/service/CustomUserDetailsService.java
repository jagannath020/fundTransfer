package com.fundTransfer.fundTransfer.service;

import com.fundTransfer.fundTransfer.entity.LoginCredentials;
import com.fundTransfer.fundTransfer.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Base64;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private LoginRepository loginRepository;

    @Override
    public UserDetails loadUserByUsername(String plainTextUsername) throws UsernameNotFoundException {
        // 1. Encode the plain-text username for lookup (to match database format)
        String encodedUsername = Base64.getEncoder().encodeToString(plainTextUsername.getBytes());

        LoginCredentials user = loginRepository.findByCustomerName(encodedUsername);

        if (user == null) {
            throw new UsernameNotFoundException("User not found: " + plainTextUsername);
        }

        // 2. Return Spring User object (username: plain text, password: stored HASH)
        return new org.springframework.security.core.userdetails.User(
                plainTextUsername,
                user.getPassword(), // This is the stored BCrypt HASH
                new ArrayList<>()
        );
    }
}