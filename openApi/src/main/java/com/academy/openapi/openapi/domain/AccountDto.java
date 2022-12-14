package com.academy.openapi.openapi.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AccountDto {

    private Long id;
    private String number;
    private String balance;


}
