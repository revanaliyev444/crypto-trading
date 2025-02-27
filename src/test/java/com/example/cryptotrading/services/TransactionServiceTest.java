package com.example.cryptotrading.services;

import com.example.cryptotrading.model.entity.Transaction;
import com.example.cryptotrading.model.entity.Wallet;
import com.example.cryptotrading.repository.TransactionRepository;
import com.example.cryptotrading.repository.WalletRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TransactionServiceTest {

    @Mock
    WalletRepository walletRepository;

    @Mock
    TransactionRepository transactionRepository;

    @InjectMocks
    TransactionService transactionService;

    Transaction transaction;

    private Wallet fromWallet;
    private Wallet toWallet;

    @BeforeEach
    void setUp() {
        fromWallet = new Wallet();
        fromWallet.setId(1L);
        fromWallet.setMoneyBalance(new BigDecimal(100));

        toWallet = new Wallet();
        toWallet.setId(2L);
        toWallet.setMoneyBalance(new BigDecimal(200));
    }

    @Test
    @Transactional
    void transferMoney() {
        BigDecimal amount = BigDecimal.valueOf(200.0);

        when(walletRepository.findById(1L)).thenReturn(Optional.of(fromWallet));
        when(walletRepository.findById(2L)).thenReturn(Optional.of(toWallet));

        transactionService.transferMoney(1L, 2L, amount);

        assertEquals(BigDecimal.valueOf(800.0), fromWallet.getMoneyBalance());
        assertEquals(BigDecimal.valueOf(700.0), toWallet.getMoneyBalance());
        verify(walletRepository, times(2)).save(any(Wallet.class));
        verify(transactionRepository, times(1)).save(any(Transaction.class));

    }

    @Test
    @Transactional
    void depositMoney() {
        BigDecimal amount = BigDecimal.valueOf(200.0);

        when(walletRepository.findById(1L)).thenReturn(Optional.of(fromWallet));
        transactionService.depositMoney(2L, amount);

        assertEquals( BigDecimal.valueOf(800.0), fromWallet.getMoneyBalance());
    }

    @Test
    void withdrawMoney() {
        BigDecimal amount = BigDecimal.valueOf(200.0);

        when(walletRepository.findById(2L)).thenReturn(Optional.of(toWallet));
        transactionService.withdrawMoney(2L, amount);

        assertEquals(BigDecimal.valueOf(300.0), toWallet.getMoneyBalance());
    }
}