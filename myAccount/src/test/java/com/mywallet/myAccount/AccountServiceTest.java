package com.mywallet.myAccount;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AccountServiceTest {
    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountService accountService;

    @Test
    public void getAllAccountsTest() {
        when(accountRepository.getAllAccounts()).thenReturn(Arrays.asList(
                new Account(1, "Steffen", 5000, "DKR",  new Date(), null),
                new Account(2, "Skywalker", 2500,"USD", new Date(), null)
        ));

        List<Account> result = accountService.getAllAccounts();

        assertEquals("Steffen", result.get(0).getUsername());
        assertEquals("USD", result.get(1).getCurrency());
    }

    @Test
    public void getUserAccountTest() {
        Account acc = new Account(1, "Steffen", 5000, "DKR",  new Date(), null);
        when(accountRepository.getUserAccount(0)).thenReturn(acc);

        Account result = accountService.getUserAccount(0, "Steffen");

        assertEquals("Steffen", result.getUsername());
        assertEquals("DKR", result.getCurrency());
    }

    @Test
    public void getUsersAccountsTest() {
        when(accountRepository.getUsersAccounts("Steffen")).thenReturn(Arrays.asList(
            new Account(1, "Steffen", 5000, "DKR",  new Date(), null),
            new Account(2, "Steffen", 2500,"USD", new Date(), null)
        ));

        List<Account> result = accountService.getUsersAccounts("Steffen");

        assertEquals("USD", result.get(1).getCurrency());
        assertEquals("DKR", result.get(0).getCurrency());
    }


    @Test
    public void createAccountTest() {
        Account acc = new Account(234, "Han", 0, "credits", null, null);
        when(accountRepository.createAccount(acc)).thenReturn(HttpStatus.CREATED);

        HttpStatus result = accountService.createAccount(acc);

        assertEquals(HttpStatus.CREATED, result);
    }

    // I know test up til now are a bit redundant, only made for practise

    @Test
    public void usernameAndIdMatchAUserTest() {
        Account acc = new Account(234, "Leia", 0, "credits", null, null);
        when(accountRepository.getUserAccount(0)).thenReturn(acc);

        boolean resultTrue = accountService.usernameAndIdMatchAUser(1, "Leia");
        boolean resultFalse = accountService.usernameAndIdMatchAUser(1, "Han");

        assertTrue(resultTrue);
        assertFalse(resultFalse);
    }


    
}