package com.academy.edu.jdbc.service.login;

import com.academy.edu.jdbc.exception.LoginFailException;

import java.util.List;

public interface UserRepository {
    List<User> findAll();

    User findByUserName(String username) throws LoginFailException;
}
