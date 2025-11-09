package com.fundTransfer.com.hcl.lloyds.ewallet.dto;

import lombok.Data;


@Data
public class UserResponseDto {
    private Integer userId;
    private String name;
    private String email;
    private String phone;

    public UserResponseDto(Integer userId, String name, String email, String phone) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

}