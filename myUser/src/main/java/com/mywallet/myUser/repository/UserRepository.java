package com.mywallet.myUser.repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.mywallet.myUser.pojo.User;

@Repository
public class UserRepository {
    private List<User> userdb = new ArrayList<>(Arrays.asList(
        new User("skywalker", "1234"),
        new User("han", "1234"),
        new User("leia", "1234")
    ));

    public List<User> getAllUsers() {
        return userdb;
    }
}
