package com.mywallet.myUser.repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.mywallet.myUser.pojo.AccountRequest;
import com.mywallet.myUser.pojo.User;

@Repository
public class UserRepository {
    private List<User> userdb = new ArrayList<>(Arrays.asList(
        new User("userOne", "skywalker", "1234"),
        new User("userTwo", "han", "1234"),
        new User("userThree", "leia", "1234")
    ));

    public List<User> getAllUsers() {
        return getDb();
    }

    public User getSingleUser(String userid) {
        return getDb().stream()
            .filter(u -> u.getUserId()
            .equals(userid))
            .findFirst()
            .orElse(null);
    }

    public List<User> getDb() {
        return userdb;
    }

    public User updateUser(User updatedUser) {
        List<User> userdb = getDb();
        User user = getSingleUser(updatedUser.getUserId());
        User newuser = new User(updatedUser.getUserId(), updatedUser.getUsername(), updatedUser.getUserPassword());
        userdb.set(userdb.indexOf(user), newuser);
        return newuser;
    }
}
