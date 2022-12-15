package com.academy.openapi.openapi.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AccountCreateRequest {
    @NotBlank
    private String number;

    @NotBlank
    @Min(0)
    private String balance;
}
