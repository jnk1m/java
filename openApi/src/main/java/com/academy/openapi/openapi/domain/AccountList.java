package com.academy.openapi.openapi.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class AccountList {
    private List<AccountDto> accountDtoList;

    public AccountList() {
        accountDtoList = new ArrayList<>();
    }
}
