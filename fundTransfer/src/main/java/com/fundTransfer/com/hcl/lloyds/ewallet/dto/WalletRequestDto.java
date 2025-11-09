package com.fundTransfer.com.hcl.lloyds.ewallet.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class WalletRequestDto {
    @NotNull
    private Integer userId;

    @NotNull
    private Integer bankId;

    private BigDecimal balance;
}
