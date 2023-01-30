package com.mywallet.myHistory.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;

import com.mywallet.myHistory.pojo.HistoryAccount;
import com.mywallet.myHistory.repository.HistoryRepository;

@Service
public class HistoryService {
    
    @Autowired
    HistoryRepository historyRepository;

    public List<HistoryAccount> getAllHistories() {
        return historyRepository.getAllHistories();
    }
    
    public HistoryAccount addHistory(HistoryAccount incomingAccount) {
        HistoryAccount newHistoryAccount = new HistoryAccount(incomingAccount);
        if (historyRepository.create(newHistoryAccount)) {
            return newHistoryAccount;
        }
        return null;
    }

    
    //deprected
    public Optional<HistoryAccount> getRecentlyUpdatedHistoryAccount(HistoryAccount account, List<HistoryAccount> history) {
        if (userHasHistory(account.getUsername())){
            return history.stream()
                .filter(acc -> acc.getUsername().equalsIgnoreCase(account.getUsername()))
                .sorted(Comparator.comparing(HistoryAccount::getLastUpdate).reversed())
                .findFirst(); 
        }
        return null;
    }

    public Boolean userHasHistory(String username) {
        return !historyRepository.getUsersHistory(username).isEmpty();
    }

    public List<HistoryAccount> getUsersHistory(String username) {
        return historyRepository.getUsersHistory(username);
    }

    
}
