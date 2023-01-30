package com.mywallet.myAccount.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mywallet.myAccount.pojo.Account;
import com.mywallet.myAccount.repository.AccountRepository;
import com.mywallet.myAccount.utility.HistoryFeignClient;

@Service
public class AccountService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    HistoryFeignClient historyFeignClient;
   
    public Account getUserAccount(int id, String user) {
        if (user.equals(accountRepository.getUserAccount(id).getUsername())) {
            return accountRepository.getUserAccount(id);
        }
        return null;
    }

    public List<Account> getAllAccounts() {
        return accountRepository.getAllAccounts();
    }

    public List<Account> getUsersAccounts(String user) {
        return accountRepository.getUsersAccounts(user);
    }
    
    public HttpStatus createAccount(Account account) {
        accountRepository.createAccount(account);
        return HttpStatus.CREATED;
    }

    public List<Account> deleteAccount(int id, String username) {
        Account acc = accountRepository.getUserAccount(id);
        if (usernameAndIdMatchAUser(acc, username)){
            accountRepository.deleteAccount(id, username);
        }
        return accountRepository.getAllAccounts();
    }

    public ResponseEntity<Account> deposit(int id, String username, double amount) {
        Account acc = accountRepository.getUserAccount(id);
        if (usernameAndIdMatchAUser(acc, username)){
            acc.setBalance(acc.getBalance() + amount);
            acc.setTransactionAmount(amount);
            acc.setLastUpdate(new Date());
            acc.setTransactionType("DEPOSIT");
            accountRepository.updateAccount(id, acc);
            historyFeignClient.addHistory(acc);
            return new ResponseEntity<Account>(acc, HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<Account>(acc, HttpStatus.BAD_REQUEST);
    }

    public boolean usernameAndIdMatchAUser(Account acc, String username) {
        return username.equalsIgnoreCase(acc.getUsername());
    }

    public ResponseEntity<Account> withdraw(int id, String username, double amount) {
        Account acc = accountRepository.getUserAccount(id);
        if (usernameAndIdMatchAUser(acc, username)){
            acc.setBalance(acc.getBalance() - amount);
            acc.setTransactionAmount(amount);
            acc.setLastUpdate(new Date());
            acc.setTransactionType("WITHDRAW");
            accountRepository.updateAccount(id, acc);
            historyFeignClient.addHistory(acc);
            return new ResponseEntity<Account>(acc, HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<Account>(acc, HttpStatus.BAD_REQUEST);
    }
}
