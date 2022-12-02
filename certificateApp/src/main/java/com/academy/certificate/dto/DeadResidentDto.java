package com.academy.certificate.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A DTO for the {@link com.academy.certificate.entity.Resident} entity
 */
@Data
public class DeadResidentDto implements Serializable {
    private final Long residentSerialNumber;
    private final LocalDate deathDate;
    private final String deathPlaceCode;
    private final String deathPlaceAddress;
}