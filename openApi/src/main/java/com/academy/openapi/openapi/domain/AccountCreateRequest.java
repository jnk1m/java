package com.academy.openapi.openapi.domain;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AccountCreateRequest {
    private String number;
    private String balance;
}
