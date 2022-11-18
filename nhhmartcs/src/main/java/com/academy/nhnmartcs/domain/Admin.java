package com.academy.nhnmartcs.domain;

import lombok.AllArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@AllArgsConstructor
public class Admin implements User{
    @NotBlank
    String id;

    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]){5,20}$")
    String password;

    @NotBlank
    String name;

    @NotNull
    boolean isAdmin;

    @Override
    public String getId(User user) {
        return this.id;
    }

    @Override
    public String getPassword(User user) {
        return this.password;
    }

    @Override
    public String getName(User user) {
        return this.name;
    }
}
