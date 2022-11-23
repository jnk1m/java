package com.academy.jdbc.board.exception;

public class LoginFailException extends Exception {
    public LoginFailException() {
        super("Please check id or password");
    }
}
