package com.mywallet.myAccount;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
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

        //this was a mess - accidently throw a whole days progress in the toilet.
    }
}
