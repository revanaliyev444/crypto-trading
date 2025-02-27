package com.example.cryptotrading.services;

import com.example.cryptotrading.model.entity.Account;
import com.example.cryptotrading.repository.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountService accountService;

    private Account account;

    @BeforeEach
    void setUp() {
        account = new Account();
        account.setId(1L);
        account.setAccountName("Test Account");
    }

    @Test
    void createAccount_savedAndReturnAccount() {
        when(accountRepository.save(any(Account.class))).thenReturn(account);
        Account savedAccount = accountService.createAccount(account);

        assertNotNull(savedAccount);
        assertEquals(savedAccount.getId(), account.getId());

        verify(accountRepository, times(1)).save(account);
    }

    @Test
    void accountId_returnsAccountId() {
        when(accountRepository.findById(1L)).thenReturn(Optional.of(account));
        Account returnAccount = accountService.getAccountById(1L);

        assertNotNull(returnAccount);
        assertEquals(returnAccount.getId(), account.getId());
    }

    @Test
    void accountId_throwException_whenAccountNotFound() {
        when(accountRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> accountService.getAccountById(1L));
        verify(accountRepository, times(1)).findById(1L);
    }

    @Test
    void updateAccount_savedAndReturnUpdateAccount() {
        Account updatedAccount = new Account();
        updatedAccount.setAccountName("Updated Account");

        when(accountRepository.findById(1L)).thenReturn(Optional.of(updatedAccount));
        when(accountRepository.save(any(Account.class))).thenReturn(updatedAccount);

        Account result = accountService.updateAccount(1L, updatedAccount);
         assertNotNull(result);
         assertEquals(result.getId(), updatedAccount.getId());

        verify(accountRepository, times(1)).findById(1L);
        verify(accountRepository, times(1)).save(updatedAccount);
    }

}