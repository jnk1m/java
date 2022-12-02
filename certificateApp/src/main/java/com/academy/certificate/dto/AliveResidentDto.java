package com.academy.certificate.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A DTO for the {@link com.academy.certificate.entity.Resident} entity
 */
@Data
public class AliveResidentDto implements Serializable {
//    private final Long residentSerialNumber;
    private final String name;
    private final String residentRegistrationNumber;
    private final String genderCode;
    private final LocalDate birthDate;
    private final String birthPlaceCode;
    private final String registrationBaseAddress;
}