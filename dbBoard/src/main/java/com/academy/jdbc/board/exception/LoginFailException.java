package com.academy.jdbc.board.exception;

public class LoginFailException extends Exception {
    public LoginFailException() {
        super("아이디 또는 비밀번호를 확인해주세요.");
    }
}
