package com.mywallet.myAccount;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

    AccountRepository accountRepository = new AccountRepository();

    @GetMapping("/accounts")
    public List<Accounts> getAllAccounts() {
        return accountRepository.getAllAccounts();
    }

    @GetMapping("/accounts/{user}")
    public List<Accounts> getAllUsersAccounts(@PathVariable String user) {
        return accountRepository.getUsersAccounts(user);
    }

    @GetMapping("/accounts/user/{id}")
    public Accounts getUserAccount(@PathVariable int id) {
        return accountRepository.getUserAccount(id);
    }

    @PutMapping("/accounts/user/1")
    public String updateUserAccount() {
        return "update user account with id 1";
    }

    @PostMapping("/accounts/user")
    public String createUserAccount() {
        return "create user account";
    }

    @DeleteMapping("/accounts/user/1")
    public String deleteUserAccount() {
        return "delete user account with id 1";
    }
}