package com.example.cryptotrading.services;

import com.example.cryptotrading.model.entity.Wallet;
import com.example.cryptotrading.repository.WalletRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class WalletService {

    WalletRepository walletRepository;

    public Wallet createWallet(Wallet wallet) {
        return walletRepository.save(wallet);
    }

    public Wallet findWalletById(Long id) {
        return walletRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Wallet not found"));
    }

    public List<Wallet> findAllWallets() {
        return walletRepository.findAll();
    }

    public Wallet updateWallet(Long id, Wallet wallet) {
        Wallet walletToUpdate = walletRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Wallet not found"));

        wallet.setCoinBalance(walletToUpdate.getCoinBalance());
        wallet.setMoneyBalance(walletToUpdate.getMoneyBalance());

        return walletRepository.save(wallet);
    }
    public void deleteWallet(Long Id) {
        walletRepository.deleteById(Id);
    }
}