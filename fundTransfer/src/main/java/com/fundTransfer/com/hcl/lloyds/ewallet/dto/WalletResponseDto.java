package com.fundTransfer.com.hcl.lloyds.ewallet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class WalletResponseDto {
    private Integer walletId;
    private Integer userId;
    private Integer bankId;
    private BigDecimal balance;
    private LocalDateTime createdAt;
}