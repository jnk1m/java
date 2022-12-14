package com.academy.backed.server.backedserver.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class AccountDto {

    private Long id;
    private String number;
    private String balance;

    public static AccountDto of(AccountDto accountDto) {
        return new AccountDto(accountDto.id, accountDto.number, accountDto.balance);
    }

}

