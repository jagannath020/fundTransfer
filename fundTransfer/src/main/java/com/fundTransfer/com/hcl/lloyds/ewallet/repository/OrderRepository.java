package com.fundTransfer.com.hcl.lloyds.ewallet.repository;

import com.fundTransfer.com.hcl.lloyds.ewallet.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
    Optional<OrderEntity> findByNameAndColorAndPrice(String name, String color, double price);

}
