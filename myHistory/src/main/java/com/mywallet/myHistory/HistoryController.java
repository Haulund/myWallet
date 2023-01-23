package com.mywallet.myHistory;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HistoryController {
    List<HistoryAccount> history = new ArrayList<>();

    @GetMapping("/history")
    public ResponseEntity<List<HistoryAccount>> getAllhistories() {
        return new ResponseEntity<>(history, HttpStatus.OK);
    }

    @PostMapping("/history")
    public ResponseEntity<HistoryAccount> addHistory(@RequestBody HistoryAccount hisAcc) {
        
        
        //service
        double currentBalance;
        double balance = hisAcc.getBalance();
        if (history.isEmpty()) {
            currentBalance = 0;
        } else {
            // filter by username;
            // sort by newest
            Optional<HistoryAccount> optOldAcc = history.stream()
                .filter(acc -> acc.getUsername().equalsIgnoreCase(hisAcc.getUsername()))
                .sorted(Comparator.comparing(HistoryAccount::getLastUpdate).reversed())
                .findFirst();

            HistoryAccount oldAcc = optOldAcc.get();
            currentBalance = oldAcc.getBalance();
        }

        double incomingBalance = hisAcc.getBalance();
        double transactioAmount = incomingBalance - currentBalance;
        HistoryAccount acc = new HistoryAccount(hisAcc.getId(), hisAcc.getUsername(), balance, "", transactioAmount, hisAcc.getCurrency(), hisAcc.getCreationDate(), hisAcc.getLastUpdate());

        //repo function
        history.add(acc);

        //controller
        return new ResponseEntity<HistoryAccount>(acc, HttpStatus.ACCEPTED);
    }
}
