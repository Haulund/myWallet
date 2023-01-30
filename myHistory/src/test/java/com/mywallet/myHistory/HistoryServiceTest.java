package com.mywallet.myHistory;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import com.mywallet.myHistory.pojo.HistoryAccount;
import com.mywallet.myHistory.service.HistoryService;

@RunWith(MockitoJUnitRunner.class)
public class HistoryServiceTest {

    @InjectMocks
    HistoryService historyService;

    
    @Test
    public void getRecentlyUpdatedHistoryAccountTest() {
        List<HistoryAccount> data = new ArrayList<>(Arrays.asList(
            new HistoryAccount(1, 1, "Luke", 50, "DEPOSIT", 50, "Credits", null, new Date(System.currentTimeMillis()-500000000)),
            new HistoryAccount(1, 1, "Luke", 100, "DEPOSIT", 50, "Credits", null, new Date(System.currentTimeMillis()-400000000)),
            new HistoryAccount(1, 1, "Luke", 150, "DEPOSIT", 50, "Credits", null, new Date(System.currentTimeMillis())
        )));

        HistoryAccount acc = data.get(0);

         
        for (HistoryAccount historyAccount : data) {
            System.out.println(historyAccount.getLastUpdate());
        } 

        Optional<HistoryAccount> result = historyService.getRecentlyUpdatedHistoryAccount(acc, data);

        System.out.println(result.get().toString());

        assertEquals(data.get(2).getLastUpdate(), result.get().getLastUpdate());
        
    }
}
