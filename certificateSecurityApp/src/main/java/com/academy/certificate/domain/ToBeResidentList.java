package com.academy.certificate.domain;

import java.time.LocalDateTime;


public interface ToBeResidentList {
    Long getResidentSerialNumber();

    String getName();

    LocalDateTime getBirthDate();

    String getGenderCode();
}
