package com.example.cryptotrading.model.dto.response;

import lombok.Data;

@Data
public class AccountDTO {
    Long id;
    String accountName;
    String accountType;
    WalletDTO walletDTO;
}
