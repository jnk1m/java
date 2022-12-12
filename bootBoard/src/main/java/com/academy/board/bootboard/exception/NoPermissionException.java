package com.academy.board.bootboard.exception;

public class NoPermissionException extends Exception {
    public NoPermissionException() {
        super("권한이 없습니다.");
    }
}
