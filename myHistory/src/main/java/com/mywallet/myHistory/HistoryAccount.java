package com.mywallet.myHistory;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter 
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class HistoryAccount {
    @Id
    private int id;
    private String username;
    private double balance;
    private String transactionType;
    private double transaction;
    private String currency;
    private Date creationDate;
    private Date lastUpdate;

}
