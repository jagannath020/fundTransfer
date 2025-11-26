package com.fundTransfer.com.hcl.lloyds.ewallet.entity;

import jakarta.persistence.Column;
import jakarta.persistence.*;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Parent")
public class ParentEntity {
    @EmbeddedId
    @Column(name = "id")
    private ParentId id;
    @Column(name = "name", unique = true)
    private String name;

    public ParentId getId() {
        return id;
    }

    public void setId(ParentId id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

@Embeddable
class ParentId {
    @Column(name = "email", unique = true)
    private String email;
    @Column(name = "phone", unique = true)
    private Integer phone;


    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ParentId)) return false;
        ParentId that = (ParentId) o;
        return email.equals(that.email) && phone.equals(that.phone);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(email, phone);
    }


}
