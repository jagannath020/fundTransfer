package com.fundTransfer.com.hcl.lloyds.ewallet.controller;


import com.fundTransfer.com.hcl.lloyds.ewallet.dto.WalletRequestDto;
import com.fundTransfer.com.hcl.lloyds.ewallet.dto.WalletResponseDto;
import com.fundTransfer.com.hcl.lloyds.ewallet.service.WalletService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/wallets")
@Tag(name = "Wallet API", description = "Manage wallets in E-Wallet system")
public class WalletController {

    @Autowired
    private WalletService walletService;

    @Operation(summary = "Create a new wallet")
    @PostMapping("/createWallet")
    public ResponseEntity<WalletResponseDto> createWallet(@Valid @RequestBody WalletRequestDto request) {
        return ResponseEntity.ok(walletService.createWallet(request));
    }

    @Operation(summary = "Get all wallets")
    @GetMapping("/getAllWallets")
    public ResponseEntity<List<WalletResponseDto>> getAllWallets() {
        return ResponseEntity.ok(walletService.getAllWallets());
    }

    @Operation(summary = "Get wallet by ID")
    @GetMapping("/{walletId}")
    public ResponseEntity<WalletResponseDto> getWalletById(@PathVariable Integer walletId) {
        return ResponseEntity.ok(walletService.getWalletById(walletId));
    }
}
