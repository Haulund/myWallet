package com.mywallet.myUser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import com.mywallet.myUser.pojo.AccountRequest;
import com.mywallet.myUser.pojo.User;
import com.mywallet.myUser.repository.UserRepository;
import com.mywallet.myUser.service.UserService;
import com.mywallet.myUser.utility.AccountFeignClient;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
    @Mock
    UserRepository userRepository;

    @Mock
    AccountFeignClient accountFeignClient;

    @InjectMocks
    UserService userService;

    @Test
    public void getAllUsersTest() {
        List<User> userdb = new ArrayList<>(Arrays.asList(
            new User("skywalker", "1234"),
            new User("han", "1234"),
            new User("leia", "1234")
        ));

        when(userRepository.getAllUsers()).thenReturn(userdb);
        
        List<User> result = userService.getAllUsers();
        assertEquals(userdb.get(0).getUsername(), result.get(0).getUsername());
        assertEquals(userdb.get(2).getUsername(), result.get(2).getUsername());
    }

    @Test
    public void getSingleUserTest() {
        List<AccountRequest> accounts = new ArrayList<>(Arrays.asList(
            new AccountRequest(1, "userOne", "skywalker", 23323.32, "", 0,"USD" ,new Date(), null),
            new AccountRequest(2, "userOne", "skywalker", 0, "", 0,"USD" ,new Date(), null ),
            new AccountRequest(3, "userTwo", "han", 832283.32, "", 0,"USD" ,new Date(), null)
        ));
        ResponseEntity<List<AccountRequest>> res = new ResponseEntity<>(accounts, HttpStatus.OK);

        when(userRepository.getSingleUser("userOne")).thenReturn(new User("userOne", "skywalker", "1234"));
        when(accountFeignClient.getAllUsersAccounts("userOne")).thenReturn(res);

        User singleUser = userService.getSingleUser("userOne");

        assertEquals("userOne", singleUser.getUserId());
        assertEquals(2, singleUser.getAccountResponse().get(1).getAccountId());
    }
    
}
