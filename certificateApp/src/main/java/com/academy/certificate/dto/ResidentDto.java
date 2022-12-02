package com.academy.certificate.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * A DTO for the {@link com.academy.certificate.entity.Resident} entity
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ResidentDto {
    private Long residentSerialNumber;

    private String name;

    private String residentRegistrationNumber;

    private String genderCode;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime birthDate;

    private String birthPlaceCode;

    private String registrationBaseAddress;
}