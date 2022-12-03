package com.academy.certificate.dto;

import com.academy.certificate.enums.BirthPlaceCode;
import com.academy.certificate.enums.GenderCode;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ResidentDto {
    private String name;

//    private String residentRegistrationNumber;

    @Enumerated(EnumType.STRING)
    private GenderCode genderCode;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime birthDate;

    @Enumerated(EnumType.STRING)
    private BirthPlaceCode birthPlaceCode;

    private String registrationBaseAddress;
}