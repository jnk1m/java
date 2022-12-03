package com.academy.certificate.dto;

import com.academy.certificate.entity.Resident;
import com.academy.certificate.enums.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class BirthReportDto {

    /*BirthDeathReportResident Entity*/
    @NotNull
    @Enumerated(EnumType.STRING)
    private BirthDeathTypeCode birthDeathTypeCode;

//    @NotNull
//    private Resident reportResidentSerialNumber;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDeathReportDate;

    @NotNull
    @Enumerated(EnumType.STRING)
    private BirthReportQualificationsCode birthReportQualificationsCode;

    @NotNull
    private String emailAddress;

    @NotNull
    private String phoneNumber;


    /*Resident Entity*/
    @NotNull
    private String name;

    //TODO #1 출생 신고 할때는 주민 등록 번호를 안 받는데 주민등록을 하려면 등록번호가 있어야 함
//    @NotNull
//    private String residentRegistrationNumber; 주민등록번호

    @NotNull
    @Enumerated(EnumType.STRING)
    private GenderCode genderCode;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime birthDate;

    @NotNull
    @Enumerated(EnumType.STRING)
    private BirthPlaceCode birthPlaceCode;

    @NotNull
    private String registrationBaseAddress;

    /*FamilyRelationship*/
    @NotNull
    private Long mother;

    @NotNull
    private Long father;


}
