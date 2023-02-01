package com.mywallet.myUser.utility;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.mywallet.myUser.pojo.AccountRequest;

@FeignClient(url = "http://localhost:8080", value = "account-feign-client")
public interface AccountFeignClient {
    
    @GetMapping("/accounts/{user}")
    public ResponseEntity<List<AccountRequest>> getAllUsersAccounts(@PathVariable String user);

    @PutMapping("/accounts/{username}/{accountId}")
    public ResponseEntity<AccountRequest> deposit(@PathVariable int accountId, @PathVariable String username, @RequestBody AccountRequest account);
    
}
