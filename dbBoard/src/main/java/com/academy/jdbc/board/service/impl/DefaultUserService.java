package com.academy.jdbc.board.service.impl;

import com.academy.jdbc.board.domain.User;
import com.academy.jdbc.board.exception.LoginFailException;
import com.academy.jdbc.board.mapper.UserMapper;
import com.academy.jdbc.board.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @Author : marco@nhnacademy.com
 * @Date : 17/05/2022
 */

@Service
public class DefaultUserService implements UserService {
    private final UserMapper userMapper;

    public DefaultUserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public Optional<User> getUser(String username) {
        return userMapper.selectUser(username);
    }

    @Override
    public Optional<User> login(String username, String password) throws LoginFailException {
        Optional<User> user = userMapper.selectUser(username);

        if (user.isEmpty()) {
            throw new LoginFailException();
        }

        checkMatchedPwd(password, user.get().getPassword());

        return user;
    }

    private void checkMatchedPwd(String password, String userPassword) throws LoginFailException {
        if (!password.equals(userPassword)) {
            throw new LoginFailException();
        }
    }
}
