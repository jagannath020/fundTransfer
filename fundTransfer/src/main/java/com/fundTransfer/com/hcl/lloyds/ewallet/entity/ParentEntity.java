package com.fundTransfer.com.hcl.lloyds.ewallet.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Parent")
public class ParentEntity {

    @EmbeddedId
    private ParentId id;

    @Column(name = "name")
    private String name;


}


@Embeddable
class ParentId {

    private String email;
    private String phone;

    public ParentId() {
    }

    public ParentId(String email, String phone) {
        this.email = email;
        this.phone = phone;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ParentId)) return false;

        ParentId that = (ParentId) o;

        return email.equals(that.email) &&
                phone.equals(that.phone);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(email, phone);
    }
}