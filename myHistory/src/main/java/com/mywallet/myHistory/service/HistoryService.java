package com.mywallet.myHistory.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mywallet.myHistory.pojo.HistoryAccount;

@Service
public class HistoryService {
    List<HistoryAccount> history = new ArrayList<>();

    public List<HistoryAccount> getAllHistories() {
        return history;
    }
    
    public HistoryAccount addHistory(HistoryAccount hisAcc) {
        //service
        double currentBalance;

        if (history.isEmpty()) {
            currentBalance = 0;
        } else {
            HistoryAccount oldAcc = getRecentlyUpdatedHistoryAccount(hisAcc, history).get();
            currentBalance = oldAcc.getBalance();
        }

        double incomingBalance = hisAcc.getBalance();
        double transactioAmount = incomingBalance - currentBalance;
        HistoryAccount acc = new HistoryAccount(
            1,
            hisAcc.getUserId(),
            hisAcc.getUsername(), 
            incomingBalance, 
            hisAcc.getTransactionType(), 
            transactioAmount, 
            hisAcc.getCurrency(), 
            hisAcc.getCreationDate(), 
            hisAcc.getLastUpdate())
        ;

        //repo function
        history.add(acc);
        return acc;
    }

    public Optional<HistoryAccount> getRecentlyUpdatedHistoryAccount(HistoryAccount account, List<HistoryAccount> history) {
        Optional<HistoryAccount> optOldAcc = history.stream()
            .filter(acc -> acc.getUsername().equalsIgnoreCase(account.getUsername()))
            .sorted(Comparator.comparing(HistoryAccount::getLastUpdate).reversed())
            .findFirst()
        ; 

        return optOldAcc;
    }
}
