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
    private String id;
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
    
    public HistoryAccount() {
        this.id = UUID.randomUUID().toString();
        this.creationDate = new Date();
    }
}
