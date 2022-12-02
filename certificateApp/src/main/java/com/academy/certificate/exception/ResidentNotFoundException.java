package com.academy.certificate.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResidentNotFoundException extends Exception {
    public ResidentNotFoundException() {
        super("주민일련번호를 확인해주세요");
    }
}
