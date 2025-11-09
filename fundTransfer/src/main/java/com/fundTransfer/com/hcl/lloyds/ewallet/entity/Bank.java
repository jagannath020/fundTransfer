package com.fundTransfer.com.hcl.lloyds.ewallet.entity;

import jakarta.persistence.*;
import lombok.Data;

    @Data
    @Entity
    @Table(name = "Bank", schema = "e_wallet")
    public class Bank {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "bank_id", nullable = false)
        private Integer bankId;

        @Column(name = "bank_name", length = 100)
        private String bankName;

        @Column(length = 100)
        private String branch;

}
