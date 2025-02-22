package com.example.cryptotrading.services;

import com.example.cryptotrading.model.entity.Transaction;
import com.example.cryptotrading.model.entity.Wallet;
import com.example.cryptotrading.model.enums.TransactionType;
import com.example.cryptotrading.repository.TransactionRepository;
import com.example.cryptotrading.repository.WalletRepository;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class TransactionService {

    WalletRepository walletRepository;

    TransactionRepository transactionRepository;

    @Transactional
    public void transferMoney(Long fromWallet_id, Long walletTo_id, BigDecimal amount) {
        Wallet fromWallet = walletRepository.findById(fromWallet_id).
                orElseThrow(() -> new RuntimeException("Wallet not found"));

        Wallet toWallet = walletRepository.findById(walletTo_id)
                .orElseThrow(() -> new RuntimeException("Wallet not found"));

        if (fromWallet.getMoneyBalance().compareTo(amount) >= 0) {
            fromWallet.setMoneyBalance(fromWallet.getMoneyBalance().subtract(amount));
            toWallet.setMoneyBalance(toWallet.getMoneyBalance().add(amount));


            walletRepository.save(fromWallet);
            walletRepository.save(toWallet);

            Transaction transaction = new Transaction();
            transaction.setFromWallet(fromWallet);
            transaction.setToWallet(toWallet);
            transaction.setAmount(amount);
            transaction.setTransactionType(TransactionType.TRANSFER);
            transaction.setCreatedDate(LocalDateTime.now());

            transactionRepository.save(transaction);
        }else {
            throw new RuntimeException("Operation unsuccessful");
        }
    }

    @Transactional
    public void depositMoney(Long walletToId, BigDecimal amount){
        Wallet wallet = walletRepository.findById(walletToId)
                .orElseThrow(() -> new RuntimeException("Wallet not found"));

        wallet.setMoneyBalance(wallet.getMoneyBalance().add(amount));
        walletRepository.save(wallet);

        Transaction transaction = new Transaction();
        transaction.setToWallet(wallet);
        transaction.setAmount(amount);
        transaction.setTransactionType(TransactionType.DEPOSIT);
        transaction.setCreatedDate(LocalDateTime.now());

        transactionRepository.save(transaction);
    }

    @Transactional
    public void withdrawMoney(Long walletToId, BigDecimal amount){
        Wallet wallet = walletRepository.findById(walletToId)
                .orElseThrow(() -> new RuntimeException("Wallet not found"));

        if (wallet.getMoneyBalance().compareTo(amount) >= 0){
            wallet.setMoneyBalance(wallet.getMoneyBalance().subtract(amount));
            walletRepository.save(wallet);

            Transaction transaction = new Transaction();
            transaction.setToWallet(wallet);
            transaction.setAmount(amount);
            transaction.setTransactionType(TransactionType.WITHDRAW);
            transaction.setCreatedDate(LocalDateTime.now());

            transactionRepository.save(transaction);
        }else {
            throw new RuntimeException("Operation unsuccessful");
        }
    }

}