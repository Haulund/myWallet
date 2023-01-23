package com.mywallet.myHistory;

import java.util.Date;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
public class HistoryAccount {
    @Getter @Setter
    @Id
    private int id;
    @Getter @Setter
    private String username;
    @Getter @Setter
    private double balance;
    @Getter @Setter
    private String transactionType;
    @Getter @Setter
    private double transaction;
    @Getter @Setter
    private String currency;
    @Getter @Setter
    private Date creationDate;
    @Getter @Setter
    private Date lastUpdate;
    
    public HistoryAccount() {

    }
    
    public HistoryAccount(int id, String username, double balance, String transactionType, double transaction, String currency, Date creationDate, Date lastUpdate) {
        this.id = id;
        this.username = username;
        this.balance = balance;
        this.transactionType = transactionType;
        this.transaction = transaction;
        this.currency = currency;
        this.creationDate = creationDate;
        this.lastUpdate = lastUpdate;
    }
}
