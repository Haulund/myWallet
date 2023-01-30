package com.mywallet.myHistory.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mywallet.myHistory.pojo.HistoryAccount;
import com.mywallet.myHistory.service.HistoryService;

import lombok.var;

@RestController
public class HistoryController {
    
    @Autowired
    HistoryService historyService;

    @GetMapping("/history")
    public ResponseEntity<List<HistoryAccount>> getAllhistories() {
        return new ResponseEntity<>(historyService.getAllHistories(), HttpStatus.OK);
    }

    @PostMapping("/history")
    public ResponseEntity<HistoryAccount> addHistory(@RequestBody HistoryAccount hisAcc) {
        var addHistoryResponse = historyService.addHistory(hisAcc);
        var status = HttpStatus.OK;

        if (addHistoryResponse == null) {
            status = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<HistoryAccount>(addHistoryResponse, status);
    }

    @GetMapping("/history/{username}")
    public ResponseEntity<List<HistoryAccount>> getUsersHistory(@PathVariable String username) {
        return new ResponseEntity<>(historyService.getUsersHistory(username), HttpStatus.OK);
    }
}
