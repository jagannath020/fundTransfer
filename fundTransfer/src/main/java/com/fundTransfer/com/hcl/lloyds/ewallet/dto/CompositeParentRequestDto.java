package com.fundTransfer.com.hcl.lloyds.ewallet.dto;

public class CompositeParentRequestDto {
    private String email;
    private Integer phone;
    private String name;

    public CompositeParentRequestDto() {
    }

    public CompositeParentRequestDto(String email, Integer phone, String name) {
        this.email = email;
        this.phone = phone;
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "CompositeParentRequestDto{" + "email='" + email + '\'' + ", phone=" + phone + ", name='" + name + '\'' + '}';
    }
}
