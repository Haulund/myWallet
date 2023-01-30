package com.mywallet.myAccount.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mywallet.myAccount.pojo.Account;
import com.mywallet.myAccount.service.AccountService;

@RestController
public class AccountController {

    @Autowired
    AccountService accountService;

    @GetMapping("/accounts")
    public ResponseEntity<List<Account>> getAllAccounts() {
        return new ResponseEntity<List<Account>>(accountService.getAllAccounts(), HttpStatus.OK);
    }

    @GetMapping("/accounts/{user}")
    public ResponseEntity<List<Account>> getAllUsersAccounts(@PathVariable String user) {
        return new ResponseEntity<List<Account>>(accountService.getUsersAccounts(user), HttpStatus.OK);
    }

    @GetMapping("/accounts/{user}/{id}")
    public ResponseEntity<Account> getUserAccount(@PathVariable int id, @PathVariable String user) {
        return new ResponseEntity<Account>(accountService.getUserAccount(id, user), HttpStatus.OK);
    }

    @PutMapping("/accounts/{userName}/{id}")
    public ResponseEntity<Account> deposit(@PathVariable int id, @PathVariable String userName, @RequestBody Account account) {
        return accountService.deposit(id, userName, account.getTransactionAmount());
    }

    @PutMapping("/accounts/withdraw/{userName}/{id}")
    public ResponseEntity<Account> withdraw(@PathVariable int id, @PathVariable String userName, @RequestBody Account account) {
        Account acc = accountService.withdraw(id, userName, account.getTransactionAmount());
        if (acc != null) {
            return new ResponseEntity<>(acc, HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<Account>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/accounts")
    public ResponseEntity<Account> createUserAccount(@RequestBody Account account) {
        HttpStatus result = accountService.createAccount(account);
        if (result == HttpStatus.CREATED) {
            return new ResponseEntity<>(account, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/accounts/{userName}/{id}")
    public ResponseEntity<List<Account>> deleteUserAccount(@PathVariable int id, @PathVariable String userName) {
        return new ResponseEntity<List<Account>>(accountService.deleteAccount(id, userName), HttpStatus.OK);
    }
}