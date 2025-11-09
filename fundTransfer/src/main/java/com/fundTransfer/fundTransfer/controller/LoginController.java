package com.fundTransfer.fundTransfer.controller;

import com.fundTransfer.fundTransfer.dto.LoginDtoRequest;
import com.fundTransfer.fundTransfer.dto.LoginDtoResponse;
import com.fundTransfer.fundTransfer.entity.LoginCredentials;
import com.fundTransfer.fundTransfer.pojo.LoginRequest;
import com.fundTransfer.fundTransfer.service.LoginService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/loginPost")
    public ResponseEntity<LoginDtoResponse> loginPost(@Valid @RequestBody LoginDtoRequest dtoRequest) {
        LoginDtoResponse response = loginService.authenticateUsingPost(dtoRequest);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<LoginCredentials> registerUser(@RequestBody LoginRequest request) {

        LoginCredentials savedUser = loginService.createNewUser(request);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    // ✅ NEW: Endpoint to fetch all users
    @GetMapping("/users") // <<< NEW SECURED ENDPOINT
    public ResponseEntity<List<LoginCredentials>> getAllUsers() {
        List<LoginCredentials> users = loginService.getAllUsers();
        // The service already returns a list with decoded usernames
        return ResponseEntity.ok(users);
    }

}
