package com.fundTransfer.com.hcl.lloyds.ewallet.service;


import com.fundTransfer.com.hcl.lloyds.ewallet.dto.BankRequestDto;
import com.fundTransfer.com.hcl.lloyds.ewallet.dto.BankResponseDto;
import com.fundTransfer.com.hcl.lloyds.ewallet.entity.Bank;
import com.fundTransfer.com.hcl.lloyds.ewallet.exception.CustomException;
import com.fundTransfer.com.hcl.lloyds.ewallet.repository.BankRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class BankService {

    @Autowired
    private BankRepository bankRepository;

    public BankResponseDto createBank(BankRequestDto request) {

        log.info("Creating bank: {}", request.getBankName());

        Bank bank = new Bank();
        bank.setBankName(request.getBankName());
        bank.setBranch(request.getBranch());

        Bank saved = bankRepository.save(bank);

        log.info("Bank saved successfully with ID: {}", saved.getBankId());

        return new BankResponseDto(saved.getBankId(), saved.getBankName(), saved.getBranch());
    }

    public List<BankResponseDto> getAllBanks() {

        log.info("Fetching all banks...");

        List<BankResponseDto> banks = bankRepository.findAll()
                .stream()
                .map(b -> new BankResponseDto(b.getBankId(), b.getBankName(), b.getBranch()))
                .collect(Collectors.toList());

        log.info("Total banks fetched: {}", banks.size());

        return banks;
    }

    public BankResponseDto getBankById(Integer bankId) {

        log.info("Fetching bank by ID: {}", bankId);

        Bank bank = bankRepository.findById(bankId)
                .orElseThrow(() -> {
                    log.error("Bank not found with id {}", bankId);
                    return new CustomException("Bank not found with id: " + bankId);
                });

        log.info("Bank found: {} - {}", bank.getBankId(), bank.getBankName());

        return new BankResponseDto(bank.getBankId(), bank.getBankName(), bank.getBranch());
    }
}