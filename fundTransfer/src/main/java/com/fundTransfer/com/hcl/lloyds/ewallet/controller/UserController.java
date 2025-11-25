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
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/users")
@Tag(name = "User Operations", description = "APIs for user management")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Create a new user")
    @PostMapping("/create")
    public ResponseEntity<UserResponseDto> createUser(@Valid @RequestBody UserRequestDto userRequestDto) {
        log.info("Received request to create user: {}", userRequestDto.getEmail());
        UserResponseDto response = userService.createUser(userRequestDto);
        log.info("User created with ID: {}", response.getUserId());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Operation(summary = "Get user by ID")
    @GetMapping("/getUserById/{userId}")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable Integer userId) {
        log.info("Fetching user with ID: {}", userId);
        UserResponseDto response = userService.getUserById(userId);
        log.info("User found: {}", response.getName());
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Delete user using deptNo, email, and phone")
    @DeleteMapping("/delete/deptNo/{deptNo}/email/{email}/phone/{phone}")
    public ResponseEntity<String> deleteUser(
            @PathVariable Integer deptNo,
            @PathVariable String email,
            @PathVariable String phone) {

        userService.deleteUser(deptNo, email, phone);
        return ResponseEntity.ok("User deleted successfully");
    }
    @Operation(summary = "Delete user using deptNo, email, and phone")
    @DeleteMapping("/deleteUser/email/{email}/phone/{phone}")
    public ResponseEntity<String> deleteUsers(
            @PathVariable String email,
            @PathVariable String phone) {

        userService.deleteUsers(email, phone);
        return ResponseEntity.ok("User deleted successfully");
    }
    @Operation(summary = "Delete user using deptNo, email, and phone")
    @DeleteMapping("/deleteUserReplica/email/{email}/phone/{phone}")
    public ResponseEntity<String> deleteUsersReplica(
            @PathVariable String email,
            @PathVariable String phone) {

        userService.deleteUsersReplica(email, phone);
        return ResponseEntity.ok("User deleted successfully");
    }


}
/**
 * HTTP Success Status Code Summary
 *
 * Code   Meaning                      When to Use
 * -----  ---------------------------- -----------------------------------------
 * 200    Success + Response Body      GET, update with body, delete with message
 * 201    Resource created             POST
 * 204    Success + No body            DELETE, PUT with no response
 */