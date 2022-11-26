package com.academy.jdbc.board.service;

import com.academy.jdbc.board.domain.User;
import com.academy.jdbc.board.exception.LoginFailException;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface UserService {
    Optional<User> getUser(String username);

    List<User> getUserList();

    Map<Integer, User> getUserMap();

    Optional<User> login(String username, String password) throws LoginFailException;


}
