package com.academy.certificate.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResidentNotFoundException extends RuntimeException {
    public ResidentNotFoundException() {
        super("존재하지 않는 주민입니다. 주민 일련번호를 확인해주세요");
    }
}
