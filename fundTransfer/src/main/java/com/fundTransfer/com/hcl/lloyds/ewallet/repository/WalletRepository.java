package com.fundTransfer.com.hcl.lloyds.ewallet.repository;


import com.fundTransfer.com.hcl.lloyds.ewallet.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepository extends JpaRepository<Wallet, Integer> {
}