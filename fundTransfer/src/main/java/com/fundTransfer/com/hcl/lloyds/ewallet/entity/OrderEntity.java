package com.fundTransfer.com.hcl.lloyds.ewallet.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(
        name = "orders",
        uniqueConstraints = @UniqueConstraint(columnNames = {"name", "color", "price"}))
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "category")
    private String category;
    @Column(name = "name")
    private String name;
    @Column(name = "color")
    private String color;
    @Column(name = "price")
    private double price;


}
