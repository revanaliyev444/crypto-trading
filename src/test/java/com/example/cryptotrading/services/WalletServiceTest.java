package com.example.cryptotrading.services;

import com.example.cryptotrading.model.entity.Wallet;
import com.example.cryptotrading.repository.WalletRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)

class WalletServiceTest {

    @Mock
    WalletRepository walletRepository;

    @InjectMocks
    WalletService walletService;

    Wallet wallet;

    @BeforeEach
    void setUp() {
        wallet = new Wallet();
        wallet.setId(1L);
        wallet.setCoinBalance(new BigDecimal(10));
        wallet.setMoneyBalance(new BigDecimal(50));
    }

    @Test
    void createWallet_ReturnsWallet() {
        when(walletRepository.save(any(Wallet.class))).thenReturn(wallet);
        Wallet createdWallet = walletService.createWallet(wallet);

        assertNotNull(createdWallet);
        assertEquals(wallet.getId(), createdWallet.getId());
        assertEquals(wallet.getMoneyBalance(), createdWallet.getMoneyBalance());
        assertEquals(wallet.getCoinBalance(), createdWallet.getCoinBalance());
    }

    @Test
    void findWalletById_returnById() {
        when(walletRepository.findById(1L)).thenReturn(Optional.of(wallet));

        assertThrows(RuntimeException.class, () -> walletService.findWalletById(1L));
        verify(walletRepository, times(1)).findById(1L);
    }

    @Test
    void updateWallet() {
        Wallet updatedWallet = new Wallet();
        updatedWallet.setMoneyBalance(new BigDecimal(10));
        updatedWallet.setCoinBalance(new BigDecimal(50));

        when(walletRepository.findById(1L)).thenReturn(Optional.of(wallet));
        when(walletRepository.save(any(Wallet.class))).thenReturn(updatedWallet);

        Wallet createdWallet = walletService.updateWallet(1L, updatedWallet);

        assertNotNull(createdWallet);
        assertEquals(new BigDecimal(50), createdWallet.getCoinBalance());
        assertEquals(new BigDecimal(10), createdWallet.getMoneyBalance());
        verify(walletRepository, times(1)).findById(1L);
        verify(walletRepository, times(1)).save(updatedWallet);
    }
}