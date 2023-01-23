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
    public ResponseEntity<List<Account>> getAllAccounts() {
        List<Account> acc = accountService.getAllAccounts();
        return new ResponseEntity<List<Account>>(acc, HttpStatus.OK);
    }

    @GetMapping("/accounts/{user}")
    public ResponseEntity<List<Account>> getAllUsersAccounts(@PathVariable String user) {
        List<Account> acc = accountService.getUsersAccounts(user);
        return new ResponseEntity<List<Account>>(acc, HttpStatus.OK);
    }

    @GetMapping("/accounts/{user}/{id}")
    public ResponseEntity<Account> getUserAccount(@PathVariable int id, @PathVariable String user) {
        Account acc = accountService.getUserAccount(id, user);
        return new ResponseEntity<Account>(acc, HttpStatus.OK);
    }

    @PutMapping("/accounts/{userName}/{id}")
    public ResponseEntity<Account> deposit(@PathVariable int id, @PathVariable String userName, @RequestBody Account account) {
        return accountService.deposit(id, userName, account.getBalance());
    }

    @PutMapping("/accounts/withdraw/{userName}/{id}")
    public ResponseEntity<Account> withdraw(@PathVariable int id, @PathVariable String userName, @RequestBody Account account) {
        return accountService.withdraw(id, userName, account.getBalance());
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
        List<Account> acc = accountService.deleteAccount(id, userName);
        return new ResponseEntity<List<Account>>(acc, HttpStatus.OK);
    }
}