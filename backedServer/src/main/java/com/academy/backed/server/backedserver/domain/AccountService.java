package com.academy.backed.server.backedserver.domain;

import com.academy.backed.server.backedserver.entity.Account;

import java.util.List;

public interface AccountService {
    List<Account> getAccounts();

    Account getAccount(Long id);

    Account createAccount(Account account);

    void deleteAccount(Long id);
}
