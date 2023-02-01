package com.mywallet.myUser.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mywallet.myUser.pojo.AccountRequest;
import com.mywallet.myUser.pojo.User;
import com.mywallet.myUser.service.UserService;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/user")
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<User> getSingleUser(@PathVariable String userId) {
        return new ResponseEntity<>(userService.getSingleUser(userId), HttpStatus.OK);
    }

    @PostMapping("user/{userId}/{accountId}")
    public ResponseEntity<User> deposit(@PathVariable String userId, @PathVariable String accountId, @RequestBody AccountRequest accountResponse) {
        return new ResponseEntity<>(userService.deposit(accountResponse), HttpStatus.OK);

        // prune 
        // need to update userID in history
    }

    // SETUP FEIGN for user actions
    
    // 2. deposit from a single user
    // 3. wihtdraw from a single user
    // 4. update user info
    // 5. update account info
    // 6. delete account


    
}
