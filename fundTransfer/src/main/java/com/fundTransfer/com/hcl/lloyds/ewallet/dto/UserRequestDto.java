package com.fundTransfer.com.hcl.lloyds.ewallet.dto;

import jakarta.validation.constraints.*;
import lombok.Data;


@Data
public class UserRequestDto {

    @NotBlank
    @Size(max = 30)
    private String name;

    @Email
    @NotBlank
    @Size(max = 100)
    private String email;

    @NotBlank
    @Pattern(regexp = "^\\d{10}$", message = "Phone number must be exactly 10 digits")
    private String phone;

    @NotNull
    private Integer deptNo;   // ✅ required field (your DB demands this)
}
