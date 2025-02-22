package com.example.cryptotrading.services;

import com.example.cryptotrading.model.entity.Account;
import com.example.cryptotrading.model.entity.AccountType;
import com.example.cryptotrading.repository.AccountTypeRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true,level = AccessLevel.PRIVATE)
public class AccountTypeService {
    AccountTypeRepository accountTypeRepository;

    public AccountType saveAccountType(AccountType accountType) {
        return accountTypeRepository.save(accountType);
    }

    public AccountType getAccountTypeById(Long id){
        return accountTypeRepository
                .findById(id)
                .orElseThrow(()-> new RuntimeException("Account not found"));
    }

    public List<AccountType> getAllAccountTypes(){
        return accountTypeRepository.findAll();
    }

    public AccountType updateAccountType(Long id, AccountType accountType){
        AccountType accountTypeToUpdate = accountTypeRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("AccountType not found"));
        accountType.setAccountType(accountTypeToUpdate .getAccountType());;;
        return accountTypeRepository.save(accountType);
    }

    public void deleteAccountType(Long Id) {
        accountTypeRepository.deleteById(Id);
    }
}