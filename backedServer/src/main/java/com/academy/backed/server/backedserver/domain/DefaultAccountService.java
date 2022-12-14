package com.academy.backed.server.backedserver.domain;

import com.academy.backed.server.backedserver.entity.Account;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DefaultAccountService implements AccountService {
    private final AccountRepository accountRepository;

    public DefaultAccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Account> getAccounts() {
        return accountRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Account getAccount(Long id) {
        return accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account not found with id : " + id));
    }

    @Override
    @Transactional
    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }

    @Override
    @Transactional
    public void deleteAccount(Long id) {
        accountRepository.deleteById(id);
    }
}
