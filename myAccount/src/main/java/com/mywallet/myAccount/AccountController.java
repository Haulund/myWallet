package com.mywallet.myAccount;

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

@RestController
public class AccountController {

    @Autowired
    AccountService accountService;

    @GetMapping("/accounts")
    public List<Account> getAllAccounts() {
        return accountService.getAllAccounts();
    }

    @GetMapping("/accounts/{user}")
    public List<Account> getAllUsersAccounts(@PathVariable String user) {
        return accountService.getUsersAccounts(user);
    }

    @GetMapping("/accounts/{user}/{id}")
    public Account getUserAccount(@PathVariable int id, @PathVariable String user) {
        return accountService.getUserAccount(id, user);
    }

    @PutMapping("/accounts/{userName}/{id}")
    public Account deposit(@PathVariable int id, @PathVariable String userName) {
        return accountService.deposit(id, userName, 500.56);
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
    public List<Account> deleteUserAccount(@PathVariable int id, @PathVariable String userName) {
        return accountService.deleteAccount(id, userName);
    }
}