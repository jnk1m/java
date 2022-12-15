package com.academy.board.bootboard.exception;

public class LoginFailException extends RuntimeException{
    public LoginFailException() {
        super("아이디 또는 비밀번호를 확인해주세요.");
    }
}
