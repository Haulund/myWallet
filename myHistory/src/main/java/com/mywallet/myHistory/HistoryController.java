package com.mywallet.myHistory;

import java.util.ArrayList;
import java.util.List;

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
        history.add(hisAcc);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
