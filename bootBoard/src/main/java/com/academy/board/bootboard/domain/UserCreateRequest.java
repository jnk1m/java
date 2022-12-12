package com.academy.board.bootboard.domain;

import com.academy.board.bootboard.entity.Role;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserCreateRequest {
    @NotNull
    @Size(min = 5, max = 15)
    private String username;

    @NotNull
    @Size(min = 8, max = 20)
    private String password;

    private Role role;

    public UserCreateRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public UserCreateRequest(String username, String password, Role role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }
}
