package com.example.cryptotrading.controller;

import com.example.cryptotrading.model.entity.Wallet;
import com.example.cryptotrading.services.WalletService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/users")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class WalletController {

    WalletService walletService;

    @PostMapping
    public ResponseEntity<Wallet> createWallet(@RequestBody Wallet wallet) {
        Wallet createdWallet = walletService.createWallet(wallet);
        return ResponseEntity.ok(createdWallet);
    }

    @GetMapping({"/Id"})
    public ResponseEntity<?> getAllWalletsById(@PathVariable Long id) {
        return ResponseEntity.ok(walletService.findWalletById(id));
    }

    @GetMapping
    public ResponseEntity<?> getAllWallet() {
        return ResponseEntity.ok(walletService.findAllWallets());
    }

//    @DeleteMapping({"/Id"})
//    public ResponseEntity<Void> deleteWalletById(@PathVariable Long id){
//        walletService.(id);
//        return ResponseEntity.ok().build();
//    }

}
