package com.fundTransfer.com.hcl.lloyds.ewallet.service;

import com.fundTransfer.com.hcl.lloyds.ewallet.dto.OrderDto;
import com.fundTransfer.com.hcl.lloyds.ewallet.entity.OrderEntity;
import com.fundTransfer.com.hcl.lloyds.ewallet.exception.DuplicateOrderException;
import com.fundTransfer.com.hcl.lloyds.ewallet.repository.OrderRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ModelMapper modelMapper;

    public OrderDto createOrder(OrderDto orderDto) {
        try {
            // Map DTO → Entity
            OrderEntity orderEntity = modelMapper.map(orderDto, OrderEntity.class);
            // Save
            OrderEntity saved = orderRepository.save(orderEntity);
            // Map Entity → DTO
            return modelMapper.map(saved, OrderDto.class);
        } catch (DataIntegrityViolationException ex) {

            throw new DuplicateOrderException(
                    "Order already exists with same name, color and price"
            );
        }
    }


    public ResponseEntity<List<OrderDto>> getOrders(Long Id) {
        List<OrderEntity> orderEntities = orderRepository.findAll();
        List<OrderDto> orderDtos = orderEntities.stream()
                .map(orderEntity -> {
                    OrderDto orderDto = new OrderDto();
                    orderDto.setCategory(orderEntity.getCategory());
                    orderDto.setName(orderEntity.getName());
                    orderDto.setColor(orderEntity.getColor());
                    orderDto.setPrice(orderEntity.getPrice());
                    return orderDto;
                })
                .collect(Collectors.toList());
        return new ResponseEntity<>(orderDtos, HttpStatus.OK);
    }


}
