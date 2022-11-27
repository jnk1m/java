package com.academy.jdbc.board.exception;

public class NoPermissionException extends Exception{
    public NoPermissionException() {
        super("권한이 없습니다.");
    }
}
