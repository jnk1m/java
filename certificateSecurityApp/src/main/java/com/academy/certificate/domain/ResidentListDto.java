package com.academy.certificate.domain;

import com.academy.certificate.enums.GenderCode;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;

public class ResidentListDto {
    private Long residentSerialNumber;

    private String name;

    @Enumerated(EnumType.STRING)
    private GenderCode genderCode;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime birthDate;
}
