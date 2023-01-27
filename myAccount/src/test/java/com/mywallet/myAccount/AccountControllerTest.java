package com.mywallet.myAccount;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.isNull;
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

import com.mywallet.myAccount.controller.AccountController;
import com.mywallet.myAccount.pojo.Account;
import com.mywallet.myAccount.service.AccountService;

/**
 * AccountControllerTest
 */
@RunWith(MockitoJUnitRunner.class)
public class AccountControllerTest {

    @Mock
    private AccountService accountService;

    @InjectMocks
    private AccountController accountController;

    @Test
    public void getAllAccountsTest() {
        List<Account> data = new ArrayList<>(Arrays.asList(
            new Account(1, "Steffen", 5000, "DKR",  new Date(), null, "", ""),
            new Account(2, "Skywalker", 2500,"USD", new Date(), null, "", "")
        ));

        when(accountService.getAllAccounts()).thenReturn(data);

        ResponseEntity<List<Account>> result = accountController.getAllAccounts();

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(data.get(1).getUsername(), result.getBody().get(1).getUsername());

    }

    @Test
    public void getAllUsersAccountsTest() {
        List<Account> data = new ArrayList<>(Arrays.asList(
            new Account(1, "userOne", 5000, "DKR",  new Date(), null, "", ""),
            new Account(2, "userOne", 2500,"USD", new Date(), null, "", "")
        ));
        when(accountService.getUsersAccounts("userOne")).thenReturn(data);

        ResponseEntity<List<Account>> result = accountController.getAllUsersAccounts("userOne");

        List<Account> resBody = result.getBody();

        assertEquals(HttpStatus.OK, result.getStatusCode());
        resBody.forEach(item -> assertEquals("userOne", item.getUsername()));

    }

    @Test
    public void getUserAccountTest() {
        when(accountService.getUserAccount(1, "Skywalker")).thenReturn(new Account(1, "Skywalker", 2500,"USD", new Date(), null, "", ""));

        ResponseEntity<Account> result = accountController.getUserAccount(1, "Skywalker");

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals("Skywalker", result.getBody().getUsername());

    }

    @Test
    public void depositTest() {
        Account inputAccount = new Account(1, "Skywalker", 500 ,"USD", new Date(), null, "", "");
        when(accountService
            .deposit(1, "Skywalker", inputAccount.getBalance()))
            .thenReturn(
                new ResponseEntity<Account>(new Account(1, "Skywalker", 3000,"USD", new Date(), null, "", "DEPOSIT"), 
                HttpStatus.ACCEPTED
            ));
            
        ResponseEntity<Account> result = accountController.deposit(1, "Skywalker", inputAccount);

        assertEquals(HttpStatus.ACCEPTED, result.getStatusCode());
        assertEquals("Skywalker", result.getBody().getUsername());
        assertEquals("DEPOSIT", result.getBody().getTransactionType());
        
    }

    @Test
    public void withdrawTest() {
        when(accountService
            .withdraw(1, "Skywalker", 250))
            .thenReturn(
                new ResponseEntity<Account>(new Account(1, "Skywalker", 2750,"USD", new Date(), null, "", "WITHDRAW"),
                HttpStatus.ACCEPTED
            )
            );

        ResponseEntity<Account> result = accountController.withdraw(1, "Skywalker", new Account(1, "Skywalker", 250,"USD", new Date(), null, "", "WITHDRAW"));

        assertEquals(HttpStatus.ACCEPTED, result.getStatusCode());
        assertEquals("Skywalker", result.getBody().getUsername());
        assertEquals("WITHDRAW", result.getBody().getTransactionType());

    }

    @Test
    public void createUserAccountTest() {
        Account newAcc = new Account(1, "Han Solo", 0,"USD", new Date(), null, "", "");
        when(accountService.createAccount(newAcc)).thenReturn(HttpStatus.CREATED);

        ResponseEntity<Account> result = accountController.createUserAccount(newAcc);

        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertEquals("Han Solo", result.getBody().getUsername());

    }
    
    @Test
    public void deleteAccountTest() {
        List<Account> data = new ArrayList<>(Arrays.asList(
            new Account(2, "Steffen", 5000, "DKR",  new Date(), null, "", ""),
            new Account(3, "Skywalker", 2500,"USD", new Date(), null, "", "")
        ));
        when(accountService.deleteAccount(1, "Luke")).thenReturn(data);

        ResponseEntity<List<Account>> result = accountController.deleteUserAccount(1, "Luke");
        
        assertEquals(data, result.getBody());
        assertEquals(HttpStatus.OK, result.getStatusCode());

    }
    
}