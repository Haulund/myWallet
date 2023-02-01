package com.mywallet.myUser.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mywallet.myUser.pojo.AccountRequest;
import com.mywallet.myUser.pojo.User;
import com.mywallet.myUser.repository.UserRepository;
import com.mywallet.myUser.utility.AccountFeignClient;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    AccountFeignClient accountFeignClient;

    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

    public User getSingleUser(String userid) {
        
        ResponseEntity<List<AccountRequest>> result = accountFeignClient.getAllUsersAccounts(userid);
        User user = userRepository.getSingleUser(userid);
        user.setAccountResponse(result.getBody());
        return user;
    }

    public User deposit(AccountRequest accountResponse) {
        ResponseEntity<AccountRequest> result = accountFeignClient.deposit(accountResponse.getAccountId(), accountResponse.getUsername(), accountResponse);
        AccountRequest accReq = result.getBody();
        List<AccountRequest> list = new ArrayList<>(Arrays.asList(accountResponse));
        User user = new User(accReq.getUserId(), accReq.getUsername(), "");
        user.setAccountResponse(list);
        return user;
    }
}
