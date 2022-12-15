package com.academy.board.bootboard.exception;

public class NoPermissionException extends RuntimeException {
    public NoPermissionException() {
        super("권한이 없습니다.");
    }
}
