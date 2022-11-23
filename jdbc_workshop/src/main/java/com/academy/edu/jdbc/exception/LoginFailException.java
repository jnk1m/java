package com.academy.edu.jdbc.exception;

public class LoginFailException extends Exception {
    public LoginFailException() {
        super("Please check id or password");
    }
}
