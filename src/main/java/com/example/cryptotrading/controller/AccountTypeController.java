package com.example.cryptotrading.controller;


import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@RequestMapping("v1/users")
public class AccountTypeController {

    private final AccountTypeService accountTypeService;

    @PostMapping
    public ResponseEntity<?> createAccountType(@RequestBody AccountType accountType) {
        AccountType savedAccountType = accountTypeService.saveAccountType(accountType);
        return ResponseEntity.ok(savedAccountType);
    }

    @GetMapping({"/Id"})
    public ResponseEntity<?> getAllAccountTypeById(@PathVariable Long id) {
        return ResponseEntity.ok(accountTypeService.getAccountTypeById(id));
    }

    @GetMapping
    public ResponseEntity<?> getAllAccountType() {
        return ResponseEntity.ok(accountTypeService.getAllAccountTypes());
    }

    @DeleteMapping({"/Id"})
    public ResponseEntity<Void> deleteAccountTypeById(@PathVariable Long id) {
        accountTypeService.deleteAccountType(id);
        return ResponseEntity.ok().build();
    }
}
