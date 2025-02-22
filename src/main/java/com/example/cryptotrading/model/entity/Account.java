package com.example.cryptotrading.model.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "accounts")
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long Id;

    @Column(unique = true, nullable = false)
    String accountName;

    @Column(nullable = false)
    String accountType;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;

    @ManyToOne
    @JoinColumn(name = "account_type_Id")
    AccountType typeOfAccount;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "account")
    Wallet wallet;


    @CreationTimestamp
    LocalDateTime createdDate;
    @UpdateTimestamp
    LocalDateTime updatedDate;

    boolean isActive = true;
}