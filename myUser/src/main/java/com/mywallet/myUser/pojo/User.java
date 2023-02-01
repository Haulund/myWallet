package com.mywallet.myUser.pojo;

import java.util.List;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
    private String userId;
    private String username;
    private String userPassword;
    private List<AccountRequest> AccountResponse;

    public User(){
        this.userId = UUID.randomUUID().toString();
    }

    public User(String username, String userPassword){
        this.userId = UUID.randomUUID().toString();
        this.username = username;
        this.userPassword = userPassword;
    }

    public User(String userid, String username, String userPassword) {
        this.userId = userid;
        this.username = username;
        this.userPassword = userPassword;
    }

    public User(String userid, String username, String userPassword, List<AccountRequest> accountResponse) {
        this.userId = userid;
        this.username = username;
        this.userPassword = userPassword;
        this.AccountResponse = accountResponse;
    }
}
