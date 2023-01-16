package com.mywallet.myAccount;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

    @GetMapping("/accounts")
    public String getAllAccounts() {
        return "All accounts";
    }

    @GetMapping("/accounts/user")
    public String getAllUsersAccounts() {
        return "All accounts for one user";
    }

    @GetMapping("/accounts/user/1")
    public String getUserAccount() {
        return "user account with id 1";
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