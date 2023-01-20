package com.mywallet.myAccount;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    HistoryFeignClient historyFeignClient;
   
    public Account getUserAccount(int index, String user) {
        if (user.equals(accountRepository.getUserAccount(index).getUsername())) {
            return accountRepository.getUserAccount(index);
        }
        return null;
    }

    public List<Account> getAllAccounts() {
        return accountRepository.getAllAccounts();
    }

    public List<Account> getUsersAccounts(String user) {
        return accountRepository.getUsersAccounts(user);
    }
    
    public HttpStatus createAccount(Account account) {
        accountRepository.createAccount(account);
        return HttpStatus.CREATED;
    }

    public List<Account> deleteAccount(int id, String username) {
        if (usernameAndIdMatchAUser(id, username)){
            accountRepository.deleteAccount(id, username);
        }
        return accountRepository.getAllAccounts();
    }

    public Account deposit(int id, String username, double amount) {
        if (usernameAndIdMatchAUser(id, username)){
            accountRepository.deposit(id, amount);
        }
        Account acc = accountRepository.getUserAccount(id-1);
        historyFeignClient.addHistory(acc);
        return acc;
    }

    public boolean usernameAndIdMatchAUser(int id, String username) {
        int index = id - 1;
        return username.equalsIgnoreCase(accountRepository.getUserAccount(index).getUsername());
    }
}
