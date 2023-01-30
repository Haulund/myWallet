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

import com.mywallet.myUser.pojo.User;
import com.mywallet.myUser.repository.UserRepository;

@RunWith(MockitoJUnitRunner.class)
public class UserRepositoryTest {
    @Mock
    UserRepository userRepo;
    
    @InjectMocks
    UserRepository userRepository;



    @Test
    public void getAllUsersTest() {
        List<User> userdb = new ArrayList<>(Arrays.asList(
            new User("skywalker", "1234"),
            new User("han", "1234"),
            new User("leia", "1234")
       ));
            
        when(userRepo.getAllUsers()).thenReturn(userdb);
            
        List<User> result = userRepository.getAllUsers();
        assertEquals(userdb.get(0).getUsername(), result.get(0).getUsername());
        assertEquals(userdb.get(2).getUsername(), result.get(2).getUsername());
    }
}
