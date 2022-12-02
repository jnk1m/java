package com.academy.certificate.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * A DTO for the {@link com.academy.certificate.entity.Resident} entity
 */
@Data
public class DeadResidentDto {
    private final Long residentSerialNumber;
    private final LocalDateTime deathDate;
    private final String deathPlaceCode;
    private final String deathPlaceAddress;
}