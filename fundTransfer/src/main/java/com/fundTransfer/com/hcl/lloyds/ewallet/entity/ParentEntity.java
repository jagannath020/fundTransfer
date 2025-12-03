package com.fundTransfer.com.hcl.lloyds.ewallet.entity;

import jakarta.persistence.Column;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Parent")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParentEntity {

    @EmbeddedId
    private ParentId id;

    @Column(name = "name")
    private String name;


}

@Data
@Embeddable
class ParentId {

    @Column(name = "email", length = 50, nullable = false)
    private String email;

    @Column(name = "phone", length = 15, nullable = false)
    private String phone;   // ✔ MUST BE STRING (DB type is VARCHAR)

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
        return email.equals(that.email) && phone.equals(that.phone);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(email, phone);
    }
}
