package com.mywallet.myHistory.pojo;

import java.util.Date;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter 
@Setter
@NoArgsConstructor
@ToString
@Entity
public class HistoryAccount {
    @Id
    private String id;
    private int accountId;
    private String userId;
    private double balance;
    private String transactionType;
    private double transactionAmount;
    private String currency;
    private Date creationDate;
    private Date lastUpdate;


    public HistoryAccount(int accountId, String userId, double balance, String transactionType, double transactionAmount, String currency, Date creationDate, Date lastUpdate) {
        this.id = UUID.randomUUID().toString();
        this.accountId = accountId;
        this.userId = userId;
        this.balance = balance;
        this.transactionType = transactionType;
        this.transactionAmount = transactionAmount;
        this.currency = currency;
        this.creationDate = creationDate;
        this.lastUpdate = lastUpdate;
    }

    public HistoryAccount(HistoryAccount acc) {
        this.id = UUID.randomUUID().toString();
        this.accountId = acc.accountId;
        this.userId = acc.userId;
        this.balance = acc.balance;
        this.transactionType = acc.transactionType;
        this.transactionAmount = acc.transactionAmount;
        this.currency = acc.currency;
        this.creationDate = acc.creationDate;
        this.lastUpdate = acc.lastUpdate;
    }

}
