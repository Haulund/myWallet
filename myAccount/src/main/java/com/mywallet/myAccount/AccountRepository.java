package com.mywallet.myAccount;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

@Repository
public class AccountRepository {
    
    private List<Account> accounts = new ArrayList<>(Arrays.asList(
        new Account(1, "userOne", 23323.32, "USD" ,new Date(), null ),
        new Account(2, "userOne", 0, "USD" ,new Date(), null ),
        new Account(3, "userTwo", 832283.32, "USD" ,new Date(), null )
    ));

    public Account getUserAccount(int index) {
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
    
    public Account createAccount(String userName, String currency) {
        Account newAcc = new Account(accounts.size() + 1, userName, 0, currency, new Date(), null);
        accounts.add(newAcc);
        return newAcc;
    }


    public void deleteAccount(int id, String username) {
        accounts.remove(accounts.get(id));
    }

    public void deposit(int id, double amount) {
        Account acc = accounts.get(id - 1);
        acc.setBalance(acc.getBalance() + amount);
    }
}