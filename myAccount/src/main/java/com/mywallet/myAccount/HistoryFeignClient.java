package com.mywallet.myAccount;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient(url = "http://localhost:8083", value = "history-feign-client")
public interface HistoryFeignClient {
    @PostMapping("/history")
    public ResponseEntity<Account> addHistory(@RequestBody Account hisAcc);
}
