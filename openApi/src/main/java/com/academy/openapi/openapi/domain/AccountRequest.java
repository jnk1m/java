package com.academy.openapi.openapi.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class AccountRequest {
    private Long id;

    public static AccountRequest of(AccountRequest accountRequest){
        return new AccountRequest(accountRequest.id);
    }
}
