package com.fundTransfer.com.hcl.lloyds.ewallet.controller;


import com.fundTransfer.com.hcl.lloyds.ewallet.dto.BankRequestDto;
import com.fundTransfer.com.hcl.lloyds.ewallet.dto.BankResponseDto;
import com.fundTransfer.com.hcl.lloyds.ewallet.service.BankService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/banks")
@Tag(name = "Bank API", description = "Manage banks in E-Wallet")
public class BankController {

    @Autowired
    private BankService bankService;

    @Operation(summary = "Create a new bank")
    @PostMapping("/createBank")
    public ResponseEntity<BankResponseDto> createBank(@Valid @RequestBody BankRequestDto request) {

        log.info("Received request to create bank: {}", request.getBankName());
        BankResponseDto response = bankService.createBank(request);
        log.info("Bank created successfully with ID: {}", response.getBankId());
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Get all banks")
    @GetMapping("/getAllBanks")
    public ResponseEntity<List<BankResponseDto>> getAllBanks() {
        log.info("Fetching all banks");
        List<BankResponseDto> banks = bankService.getAllBanks();
        log.info("Total banks fetched: {}", banks.size());
        return ResponseEntity.ok(banks);
    }

    @Operation(summary = "Get bank by ID")
    @GetMapping("/{bankId}")
    public ResponseEntity<BankResponseDto> getBankById(@PathVariable Integer bankId) {
        log.info("Fetching bank details for ID: {}", bankId);
        BankResponseDto response = bankService.getBankById(bankId);
        log.info("Bank found: {}", response.getBankName());
        return ResponseEntity.ok(response);
    }
}
