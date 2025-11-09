package com.fundTransfer.fundTransfer.entity;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "login_tbl")
public class LoginCredentials {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="customer_id")
    private Integer customerId;
    @Column(name = "transaction_id")
    private Integer transactionId;
    @Column(name = "username", nullable = false, unique = true)
    private String customerName;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "creation_date")
    private Date creationDate;


    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "LoginCredentials{" +
                "customerId=" + customerId +
                ", transactionId=" + transactionId +
                ", customerName='" + customerName + '\'' +
                ", password='" + password + '\'' +
                ", creationDate=" + creationDate +
                '}';
    }
}
