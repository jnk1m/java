package com.academy.certificate.domain;

import com.academy.certificate.enums.BirthPlaceCode;
import com.academy.certificate.enums.BirthReportQualificationsCode;
import com.academy.certificate.enums.GenderCode;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface BirthCertification {
    String getName();

    GenderCode getGenderCode();

    LocalDateTime getBirthDate();

    BirthPlaceCode getBirthPlaceCode();

    LocalDate getBirthDeathReportDate();

    String getReportResidentSerialNumber();

    BirthReportQualificationsCode getBirthReportQualificationsCode();

    String getEmailAddress();

    String getPhoneNumber();

}
