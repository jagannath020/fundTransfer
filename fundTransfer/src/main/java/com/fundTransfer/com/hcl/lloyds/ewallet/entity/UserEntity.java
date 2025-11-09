package com.fundTransfer.com.hcl.lloyds.ewallet.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;


@Data
@Entity
@Table(name = "User", schema = "e_wallet")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false, unique = true)
    private Integer userId;

    @Column(nullable = false, length = 30, unique = true)
    private String name;

    @Column(nullable = false, unique = true, length = 30)
    private String email;

    @Column(length = 10)
    @jakarta.validation.constraints.Pattern(
            regexp = "^\\d{10}$",
            message = "Phone number must be exactly 10 digits"
    )
    private String phone;

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();



}