package com.academy.board.bootboard.service;

import com.academy.board.bootboard.domain.UserCreateRequest;
import com.academy.board.bootboard.entity.User;
import com.academy.board.bootboard.exception.LoginFailException;

public interface UserService {
    User getUser(String username) throws LoginFailException;

    User createUser(UserCreateRequest userCreateRequest);

}
