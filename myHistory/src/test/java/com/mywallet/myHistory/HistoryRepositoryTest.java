package com.mywallet.myHistory;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.mywallet.myHistory.pojo.HistoryAccount;
import com.mywallet.myHistory.repository.HistoryRepository;

@RunWith(MockitoJUnitRunner.class)
public class HistoryRepositoryTest {
    @Mock
    HistoryRepository mockHistoryRepository;
    
    @InjectMocks
    HistoryRepository historyRepository;

    @Test
    public void getIndexTest() {
        List<HistoryAccount> data = new ArrayList<>(Arrays.asList(
            new HistoryAccount(1, "Luke", 50, "DEPOSIT", 50, "Credits", null, new Date(System.currentTimeMillis()-500000000)),
            new HistoryAccount(1, "Luke", 100, "DEPOSIT", 50, "Credits", null, new Date(System.currentTimeMillis()-400000000)),
            new HistoryAccount(1, "Luke", 150, "DEPOSIT", 50, "Credits", null, new Date(System.currentTimeMillis())
        )));
        
        int indexResult = historyRepository.getIndex(data.get(0), data);

        assertEquals(data.get(0).getId(), data.get(indexResult).getId());
    }
}
