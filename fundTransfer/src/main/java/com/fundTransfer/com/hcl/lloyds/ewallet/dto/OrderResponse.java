package com.fundTransfer.com.hcl.lloyds.ewallet.dto;

import lombok.Data;

@Data
public class OrderResponse {

    private String message;
    private OrderDto order;

    public OrderResponse(String message, OrderDto order) {
        this.message = message;
        this.order = order;
    }

}