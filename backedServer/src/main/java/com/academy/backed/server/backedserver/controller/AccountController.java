package com.academy.backed.server.backedserver.controller;

import com.academy.backed.server.backedserver.domain.AccountService;
import com.academy.backed.server.backedserver.entity.Account;
import com.sun.istack.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/accounts")
public class AccountController {
    private final AccountService accountService;

    @GetMapping
    public List<Account> getAccounts(){
        return accountService.getAccounts();
    }

    @GetMapping("/{id}")
    public Account getAccount(@PathVariable Long id){
        return accountService.getAccount(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Account createAccount(@NotNull @RequestBody Account account){
        return accountService.createAccount(account);
    }

    @DeleteMapping("/{id}")
    public String deleteAccount(@PathVariable Long id){
        accountService.deleteAccount(id);
        return "{\"result \":\"OK\"}";
    }
}
