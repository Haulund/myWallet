package com.mywallet.myAccount.pojo;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter
@AllArgsConstructor
@Entity
public class Account {

    @Id
    private int accountId;
    private String username;
    private double balance;
    private String currency;
    private Date creationDate;
    private Date lastUpdate;
    private double transactionAmount;
    private String transactionType;

    public Account() {
        this.creationDate = new Date();
    }

    //change Id generation
    //this..id = UUID.randomUUID().toString();
}
