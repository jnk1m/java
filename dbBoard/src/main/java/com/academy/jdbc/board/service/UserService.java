package com.academy.jdbc.board.service;

import com.academy.jdbc.board.domain.User;
import com.academy.jdbc.board.exception.LoginFailException;

import java.util.Optional;

public interface UserService {
    Optional<User> getUser(String username);

    Optional<User> login(String username, String password) throws LoginFailException;


}
