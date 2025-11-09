package com.fundTransfer.com.hcl.lloyds.ewallet.controller;

import com.fundTransfer.com.hcl.lloyds.ewallet.dto.UserRequestDto;
import com.fundTransfer.com.hcl.lloyds.ewallet.dto.UserResponseDto;
import com.fundTransfer.com.hcl.lloyds.ewallet.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/users")
@Tag(name = "User Operations", description = "APIs for user management")
public class UserController {

    @Autowired
    private UserService userService;

    @Operation(summary = "Create new user")
    @PostMapping("/create")
    public ResponseEntity<UserResponseDto> createUser(@Valid @RequestBody UserRequestDto userRequestDto) {
        log.info("Received request to create user: {}", userRequestDto.getEmail());
        UserResponseDto response = userService.createUser(userRequestDto);
        log.info("User created successfully with ID: {}", response.getUserId());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
