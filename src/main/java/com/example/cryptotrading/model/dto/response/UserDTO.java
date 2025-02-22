package com.example.cryptotrading.model.dto.response;

import lombok.Data;

import java.util.Set;

@Data
public class UserDTO {
    Long id;
    String username;
    String name;
    String surname;
    String email;
    Set<AccountDTO> accounts;
}
