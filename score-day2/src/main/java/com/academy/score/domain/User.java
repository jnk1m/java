package com.academy.score.domain;

import lombok.AllArgsConstructor;
import lombok.Value;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Value
@AllArgsConstructor
public class User {
    @Email
    String email;
    String password;
}
