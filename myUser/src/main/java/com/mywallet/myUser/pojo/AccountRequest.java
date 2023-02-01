package com.mywallet.myUser.pojo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
public class AccountRequest {
    private int accountId;
    private String userId;
    private String username;
    private double balance;
    private String transactionType;
    private double transactionAmount;
    private String currency;
    private Date creationDate;
    private Date lastUpdate;
}
