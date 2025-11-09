package com.fundTransfer.com.hcl.lloyds.ewallet.repository;


import com.fundTransfer.com.hcl.lloyds.ewallet.entity.Bank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankRepository extends JpaRepository<Bank, Integer> {
}
