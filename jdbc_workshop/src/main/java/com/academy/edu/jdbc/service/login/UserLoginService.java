package com.academy.edu.jdbc.service.login;

import com.academy.edu.jdbc.exception.LoginFailException;

public interface UserLoginService {
    User login(String username, String password) throws LoginFailException;
}
