package com.fundTransfer.com.hcl.lloyds.ewallet.service;


import com.fundTransfer.com.hcl.lloyds.ewallet.dto.WalletRequestDto;
import com.fundTransfer.com.hcl.lloyds.ewallet.dto.WalletResponseDto;
import com.fundTransfer.com.hcl.lloyds.ewallet.entity.Bank;
import com.fundTransfer.com.hcl.lloyds.ewallet.entity.UserEntity;
import com.fundTransfer.com.hcl.lloyds.ewallet.entity.Wallet;
import com.fundTransfer.com.hcl.lloyds.ewallet.exception.CustomException;
import com.fundTransfer.com.hcl.lloyds.ewallet.repository.BankRepository;
import com.fundTransfer.com.hcl.lloyds.ewallet.repository.UserRepository;
import com.fundTransfer.com.hcl.lloyds.ewallet.repository.WalletRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WalletService {
    private static final Logger logger = LoggerFactory.getLogger(WalletService.class);

    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BankRepository bankRepository;

    public WalletResponseDto createWallet(WalletRequestDto dto) {
        logger.info("Creating wallet for userId={} and bankId={}", dto.getUserId(), dto.getBankId());
        UserEntity user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new CustomException("User not found"));

        Bank bank = bankRepository.findById(dto.getBankId())
                .orElseThrow(() -> new CustomException("Bank not found"));

        Wallet wallet = new Wallet();
        wallet.setUser(user);
        wallet.setBank(bank);
        wallet.setBalance(dto.getBalance() == null ? java.math.BigDecimal.ZERO : dto.getBalance());
        Wallet saved = walletRepository.save(wallet);

        logger.debug("Wallet created: {}", saved.getWalletId());

        return new WalletResponseDto(
                saved.getWalletId(),
                saved.getUser().getUserId(),
                saved.getBank().getBankId(),
                saved.getBalance(),
                saved.getCreatedAt()
        );
    }

    public List<WalletResponseDto> getAllWallets() {
        logger.info("Getting all wallets");
        return walletRepository.findAll().stream()
                .map(w -> new WalletResponseDto(
                        w.getWalletId(),
                        w.getUser().getUserId(),
                        w.getBank().getBankId(),
                        w.getBalance(),
                        w.getCreatedAt()))
                .collect(Collectors.toList());
    }

    public WalletResponseDto getWalletById(Integer id) {
        Wallet w = walletRepository.findById(id)
                .orElseThrow(() -> new CustomException("Wallet not found"));
        return new WalletResponseDto(
                w.getWalletId(),
                w.getUser().getUserId(),
                w.getBank().getBankId(),
                w.getBalance(),
                w.getCreatedAt());
    }
}