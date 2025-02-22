package com.example.cryptotrading.controller;

import com.example.cryptotrading.model.entity.Account;
import com.example.cryptotrading.services.AccountService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/users")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AccountController {

    AccountService accountService;

    @PostMapping
    public ResponseEntity<?> createAccount(@RequestBody Account account){
        Account createdAcc = accountService.createAccount(account);
        return ResponseEntity.ok(createdAcc);
    }

    @GetMapping
    public ResponseEntity<?> getAllAccounts(){
        return ResponseEntity.ok(accountService.getAllAccounts());
    }

    @GetMapping({"Id"})
    public ResponseEntity<?> getAccountById(@PathVariable Long id){
        return ResponseEntity.ok(accountService.getAccountById(id));
    }

//    @DeleteMapping({"/Id"})
//    public ResponseEntity<Void> deleteAccountById(@PathVariable Long id){
//        accountService
//    }

}