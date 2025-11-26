package com.fundTransfer.com.hcl.lloyds.ewallet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    private double price;
    private String category;
    private String name;
    private String color;
    private Long id;
}
