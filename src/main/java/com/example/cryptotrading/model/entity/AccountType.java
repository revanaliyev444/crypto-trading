package com.example.cryptotrading.model.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Entity
@Table(name = "accountTypes")
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AccountType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long Id;

    String accountType;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "accountType")
    Set<Account> accounts;
}