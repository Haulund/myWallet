package com.mywallet.myAccount;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
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
import org.springframework.http.ResponseEntity;

import com.mywallet.myAccount.pojo.Account;
import com.mywallet.myAccount.repository.AccountRepository;
import com.mywallet.myAccount.service.AccountService;
import com.mywallet.myAccount.utility.HistoryFeignClient;

@RunWith(MockitoJUnitRunner.class)
public class AccountServiceTest {
    @Mock
    private AccountRepository accountRepository;

    @Mock 
    private HistoryFeignClient historyFeignClient;

    @InjectMocks
    private AccountService accountService;

    @Test
    public void getAllAccountsTest() {
        // Mock data
        List<Account> data = new ArrayList<>(Arrays.asList(
            new Account(1, "Steffen", 5000, "DKR",  new Date(), null, "", ""),
            new Account(2, "Skywalker", 2500,"USD", new Date(), null, "", "")
        ));

        // Arrange
        when(accountRepository.getAllAccounts()).thenReturn(data);

        // Act
        List<Account> result = accountService.getAllAccounts();

        // Assert
        assertEquals("Steffen", result.get(0).getUsername());
        assertEquals("USD", result.get(1).getCurrency());
    }

    @Test
    public void getUserAccountTest() {
        // Mock Data
        Account acc = new Account(1, "Steffen", 5000, "DKR",  new Date(), null, "", "");

        // Arrange
        when(accountRepository.getUserAccount(0)).thenReturn(acc);

        // Act
        Account result = accountService.getUserAccount(0, "Steffen");

        // Assert
        assertEquals("Steffen", result.getUsername());
        assertEquals("DKR", result.getCurrency());
    }

    @Test
    public void getUsersAccountsTest() {
        // Data
        List<Account> data = new ArrayList<>(Arrays.asList(
            new Account(1, "Steffen", 5000, "DKR",  new Date(), null, "", ""),
            new Account(2, "Steffen", 2500,"USD", new Date(), null, "", "")
        ));

        // Arrange
        when(accountRepository.getUsersAccounts("Steffen")).thenReturn(data);

        // Act
        List<Account> result = accountService.getUsersAccounts("Steffen");

        // Assert
        assertEquals("USD", result.get(1).getCurrency());
        assertEquals("DKR", result.get(0).getCurrency());
    }


    @Test
    public void createAccountTest() {
        // Mock Data
        Account acc = new Account(234, "Han", 0, "credits", null, null, "", "");

        // Arrange
        when(accountRepository.createAccount(acc)).thenReturn(HttpStatus.CREATED);

        // Act
        HttpStatus result = accountService.createAccount(acc);

        // Assert
        assertEquals(HttpStatus.CREATED, result);
    }

    // I know test up til now are a bit redundant, only made for practise

    @Test
    public void usernameAndIdMatchAUserTest() {
        // Mock Data
        Account acc = new Account(234, "Leia", 0, "credits", null, null, "", "");
        
        // Act
        boolean resultTrue = accountService.usernameAndIdMatchAUser(acc, "Leia");
        boolean resultFalse = accountService.usernameAndIdMatchAUser(acc, "Han");

        // Assert
        assertTrue(resultTrue);
        assertFalse(resultFalse);
    }

    @Test
    public void depositTest() {
        //Mock Data
        Account acc = new Account(1, "Chewie", 0, "credits", null, null, "", "");
        ResponseEntity<Account> res = new ResponseEntity<Account>(new Account(1, "Chewie", 250, "credits", null, null, "", ""), HttpStatus.ACCEPTED);
        
        //Arrange
        when(accountRepository.getUserAccount(1)).thenReturn(acc);
        doNothing().when(accountRepository).updateAccount(1, acc);
        when(historyFeignClient.addHistory(acc)).thenReturn(res);

        //act
        ResponseEntity<Account> result = accountService.deposit(1, "Chewie", 250);

        //assert
        assertTrue(250 == result.getBody().getBalance());
        assertTrue(result.getBody().getLastUpdate() != null);
        
    }

    @Test
    public void withdrawTest() {
        //Mock Data
        Account acc = new Account(1, "Anakin", 0, "credits", null, null, "", "");
        ResponseEntity<Account> res = new ResponseEntity<Account>(new Account(1, "Anakin", 500, "credits", null, null, "" ,""), HttpStatus.ACCEPTED);
        
        //Arrange
        when(accountRepository.getUserAccount(1)).thenReturn(acc);
        doNothing().when(accountRepository).updateAccount(1, acc);
        when(historyFeignClient.addHistory(acc)).thenReturn(res);

        //act
        ResponseEntity<Account> result = accountService.deposit(1, "Anakin", 250);

        //assert
        assertTrue(250 == result.getBody().getBalance());
        assertTrue(result.getBody().getLastUpdate() != null);
    }

    
}