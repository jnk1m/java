package com.academy.board.bootboard.domain;

import com.academy.board.bootboard.entity.Role;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class UserCreateRequest {
    @NotNull
    @Size(min = 5, max = 15)
    private final String username;

    @NotNull
    @Size(min = 8, max = 20)
    private final String password;

    public UserCreateRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
