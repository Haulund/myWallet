package com.mywallet.myAccount.utility;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.mywallet.myAccount.pojo.Account;


@FeignClient(url = "http://localhost:8083", value = "history-feign-client")
public interface HistoryFeignClient {
    @PostMapping("/history")
    public ResponseEntity<Account> addHistory(@RequestBody Account hisAcc);
}
