package com.mywallet.myHistory.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.mywallet.myHistory.pojo.HistoryAccount;

@Repository
public class HistoryRepository {
    List<HistoryAccount> history = new ArrayList<>();

    public List<HistoryAccount> getAllHistories() {
        return history;
    }

    public HistoryAccount update(HistoryAccount acc) {
        return history.set(getIndex(acc, history), acc);
    }

    public Boolean create(HistoryAccount acc) {
        return history.add(acc);
    }

    public List<HistoryAccount> getUsersHistory(String username){
        return history.stream()
            .filter(acc -> acc.getUserId().equalsIgnoreCase(username))
            .collect(Collectors.toList());
    }

    public int getIndex(HistoryAccount acc, List<HistoryAccount> historyList) {
        return historyList.indexOf(acc);
    }
}
