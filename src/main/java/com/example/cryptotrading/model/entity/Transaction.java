package com.example.cryptotrading.model.entity;

import com.example.cryptotrading.model.enums.TransactionType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long Id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "walletFrom_id")
    Wallet fromWallet;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "toWallet_id")
    Wallet toWallet;

    BigDecimal amount;

    @Enumerated(EnumType.STRING)
    TransactionType transactionType;

    @CreationTimestamp
    LocalDateTime createdDate;
    @UpdateTimestamp
    LocalDateTime updatedDate;

}