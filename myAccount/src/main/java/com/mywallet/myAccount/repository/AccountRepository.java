package com.mywallet.myAccount.repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import com.mywallet.myAccount.pojo.Account;

@Repository
public class AccountRepository {
    
    private List<Account> accounts = new ArrayList<>(Arrays.asList(
        new Account(1, "userOne", 23323.32, "USD" ,new Date(), null ,"", ""),
        new Account(2, "userOne", 0, "USD" ,new Date(), null ,"", ""),
        new Account(3, "userTwo", 832283.32, "USD" ,new Date(), null, "", "")
    ));

    public Account getUserAccount(int id) {
        int index = id - 1;
        return accounts.get(index);
    }

    public List<Account> getAllAccounts() {
        return accounts;
    }

    public List<Account> getUsersAccounts(String user) {
        return accounts.stream()
            .filter(acc -> acc.getUsername().equalsIgnoreCase(user))
            .collect(Collectors.toList());
    }
    
    public HttpStatus createAccount(Account acc) {
        accounts.add(acc);
        return HttpStatus.CREATED;
    }


    public void deleteAccount(int id, String username) {
        int index = id-1;
        accounts.remove(accounts.get(index));
    }

    public void deposit(int id, double amount) {
        Account acc = accounts.get(id - 1);
        acc.setBalance(acc.getBalance() + amount);
    }

    public void updateAccount(int id, Account acc) {
        int index = id - 1;
        accounts.set(index, acc);
    }
}