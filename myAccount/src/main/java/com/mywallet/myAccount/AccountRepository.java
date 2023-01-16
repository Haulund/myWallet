package com.mywallet.myAccount;

import java.util.Arrays;
import java.util.List;

public class AccountRepository {
    private List<Accounts> accounts = Arrays.asList(
        new Accounts(1, "userOne", 23323.32, "USD" ,null, null ),
        new Accounts(2, "userOne", 233.32, "USD" ,null, null ),
        new Accounts(3, "userTwo", 832283.32, "USD" ,null, null )
        );


    public Accounts getUserAccount(int index) {
        return accounts.get(index-1);
    }

    public List<Accounts> getAllAccounts() {
        return accounts;
    }

    public List<Accounts> getUsersAccounts(String user) {
        // Thise needs to be changed
        return accounts;
    }
}
