package com.mywallet.myAccount;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
public class Accounts {
    @Getter @Setter
    @Id
    private int id;
    @Getter @Setter
    private String username;
    @Getter @Setter
    private double balance;
    @Getter @Setter
    private String currency;
    @Getter @Setter
    private Date creationDate;
    @Getter @Setter
    private Date lastUpdate;
    
    public Accounts(int id, String username, double balance, String currency, Date creationDate, Date lastUpdate) {
        this.id = id;
        this.username = username;
        this.balance = balance;
        this.currency = currency;
        this.creationDate = creationDate;
        this.lastUpdate = lastUpdate;
    }
}
