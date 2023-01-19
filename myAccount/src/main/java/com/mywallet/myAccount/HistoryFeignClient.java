package com.mywallet.myAccount;

import org.springframework.cloud.openfeign.FeignClient;


@FeignClient(url = "http://localhost:8083", value = "history-feign-client")
public interface HistoryFeignClient {
    @PostMapping("/history")
    public ResponseEntity<HistoryAccount> addHistory(@RequestBody HistoryAccount hisAcc)
}
