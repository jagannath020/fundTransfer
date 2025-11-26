package com.fundTransfer.com.hcl.lloyds.ewallet.controller;

import com.fundTransfer.com.hcl.lloyds.ewallet.dto.OrderDto;
import com.fundTransfer.com.hcl.lloyds.ewallet.dto.OrderResponse;
import com.fundTransfer.com.hcl.lloyds.ewallet.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private final OrderService orderService;

    @PostMapping("/orders")
    public ResponseEntity<OrderResponse> createOrder(@RequestBody OrderDto orderDto) {
        OrderDto savedOrder = orderService.createOrder(orderDto);

        OrderResponse response = new OrderResponse(
                "New order is created",
                savedOrder
        );

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/getOrders/Id/{Id}")
    public ResponseEntity<List<OrderDto>> getOrders(@PathVariable Long Id) {
        return orderService.getOrders(Id);
    }
}
