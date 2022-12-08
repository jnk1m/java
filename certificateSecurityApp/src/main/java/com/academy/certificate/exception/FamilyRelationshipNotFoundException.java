package com.academy.certificate.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class FamilyRelationshipNotFoundException extends Exception{
    public FamilyRelationshipNotFoundException() {
        super("가족 관계를 찾을 수 없습니다. 주민 일련번호를 확인해주세요.");
    }
}
