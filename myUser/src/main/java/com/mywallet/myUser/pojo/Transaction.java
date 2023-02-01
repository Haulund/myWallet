package com.mywallet.myUser.pojo;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Transaction {
    private String transactionId;
    private String transacionType;
    private String userId;
    private int accountId;
    private double amount;

    public Transaction(String transactionType, String userId, int accountId, double amount) {
        this.transactionId = UUID.randomUUID().toString();
        this.transacionType = transactionType;
        this.userId = userId;
        this.accountId = accountId;
        this.amount = amount;
    }
}


