package com.fundTransfer.fundTransfer.dto;

import jakarta.validation.constraints.NotBlank;

public class LoginDtoRequest {
    @NotBlank
    private String customerName;
    @NotBlank
    private String password;

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "LoginDtoRequest{" +
                "customerName='" + customerName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
