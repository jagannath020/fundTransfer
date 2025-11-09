package com.fundTransfer.com.hcl.lloyds.ewallet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class BankResponseDto {
    private Integer bankId;
    private String bankName;
    private String branch;
}