package com.academy.certificate.domain;

import com.academy.certificate.enums.BirthPlaceCode;
import com.academy.certificate.enums.GenderCode;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;

public class ModifyBirthReportDto {
    private String emailAddress;

    private String phoneNumber;

    private String name;

    private GenderCode genderCode;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime birthDate;

    @Enumerated(EnumType.STRING)
    private BirthPlaceCode birthPlaceCode;

    private String registrationBaseAddress;
}
