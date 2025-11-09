package com.fundTransfer.com.hcl.lloyds.ewallet.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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

    @Size(max = 15)
    private String phone;

}