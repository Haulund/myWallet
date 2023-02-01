package com.mywallet.myUser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import com.mywallet.myUser.controller.UserController;
import com.mywallet.myUser.pojo.User;
import com.mywallet.myUser.service.UserService;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

    @Mock
    UserService userService;

    @InjectMocks
    UserController userController;
    
    @Test
    public void getAllUsersTest() {
        List<User> userdb = new ArrayList<>(Arrays.asList(
            new User("skywalker", "1234"),
            new User("han", "1234"),
            new User("leia", "1234")
        ));
        when(userService.getAllUsers()).thenReturn(userdb);
        ResponseEntity<List<User>> result = userController.getAllUsers();
        assertEquals(userdb.get(0).getUserId(), result.getBody().get(0).getUserId());
        assertEquals(userdb.get(1).getUsername(), result.getBody().get(1).getUsername());
    }

    @Test
    public void getSingleUserTest() {
        when(userService.getSingleUser("userIdOne")).thenReturn(new User("userIdOne", "skywalker", "1234"));
        ResponseEntity<User> result = userController.getSingleUser("userIdOne");
        assertEquals("userIdOne", result.getBody().getUserId());
    }

}
