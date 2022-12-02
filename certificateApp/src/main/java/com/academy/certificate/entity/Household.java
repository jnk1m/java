package com.academy.certificate.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "household")
public class Household {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "household_serial_number", nullable = false)
    private Long householdSerialNumber;

    @Getter(AccessLevel.NONE)
    @ManyToOne(optional = false)
    @JoinColumn(name = "resident_serial_number", nullable = false)
    private Resident householdResidentSerialNumber;

    @Column(name = "household_composition_date", nullable = false)
    private LocalDate householdCompositionDate;

    @Column(name = "household_composition_reason_code", nullable = false, length = 20)
    private String householdCompositionReasonCode;

    @Column(name = "current_house_movement_address", nullable = false, length = 500)
    private String currentHouseMovementAddress;


}