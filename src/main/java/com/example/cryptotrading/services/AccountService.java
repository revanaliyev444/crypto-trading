package com.example.cryptotrading.services;

import com.example.cryptotrading.model.entity.Account;
import com.example.cryptotrading.repository.AccountRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class AccountService {

    AccountRepository accountRepository;

    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }

    public Account getAccountById(Long id){
        return accountRepository
                .findById(id)
                .orElseThrow(()-> new RuntimeException("Account not found"));
    }

    public List<Account> getAllAccounts(){
        return accountRepository.findAll();
    }

    public Account updateAccount(Long id, Account account){
        Account accountToUpdate = accountRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found"));
        account.setAccountName(accountToUpdate .getAccountName());
        return accountRepository.save(account);
    }

    public void deleteAccount(Long id){
         accountRepository.deleteById(id);
    }
}