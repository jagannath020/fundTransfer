package com.fundTransfer.fundTransfer.repository;

import com.fundTransfer.fundTransfer.entity.DetailsTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetailsTransactionRepository extends JpaRepository<DetailsTransaction, Integer> {
    DetailsTransaction findByTransactionId(Integer transactionId);
}
