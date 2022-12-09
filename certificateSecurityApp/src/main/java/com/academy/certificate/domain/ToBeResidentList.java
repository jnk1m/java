package com.academy.certificate.domain;

import lombok.ToString;

import java.time.LocalDateTime;


public interface ToBeResidentList {
    Long getResidentSerialNumber();
    String getName();
    LocalDateTime getBirthDate();
    String getGenderCode();
}
