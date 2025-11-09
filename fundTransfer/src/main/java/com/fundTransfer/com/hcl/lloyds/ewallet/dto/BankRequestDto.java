package com.fundTransfer.com.hcl.lloyds.ewallet.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Data
public class BankRequestDto {
    @NotBlank(message = "Bank name is required")
    @Size(max = 100)
    private String bankName;

    @NotBlank(message = "Branch is required")
    @Size(max = 100)
    private String branch;
}