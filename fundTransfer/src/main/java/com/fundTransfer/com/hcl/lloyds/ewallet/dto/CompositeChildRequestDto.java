package com.fundTransfer.com.hcl.lloyds.ewallet.dto;

public class CompositeChildRequestDto {
    private Integer childId;
    private String email;
    private Integer phone;
    private String childName;

    public CompositeChildRequestDto() {
    }

    public CompositeChildRequestDto(Integer childId, String email, Integer phone, String childName) {
        this.childId = childId;
        this.email = email;
        this.phone = phone;
        this.childName = childName;
    }

    public Integer getChildId() {
        return childId;
    }

    public void setChildId(Integer childId) {
        this.childId = childId;
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

    public String getChildName() {
        return childName;
    }

    public void setChildName(String childName) {
        this.childName = childName;
    }

    @Override
    public String toString() {
        return "CompositeChildRequestDto{" +
                "childId=" + childId +
                ", email='" + email + '\'' +
                ", phone=" + phone +
                ", childName='" + childName + '\'' +
                '}';
    }
}
