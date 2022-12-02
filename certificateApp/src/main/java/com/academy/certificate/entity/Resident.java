package com.academy.certificate.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "resident")
@Getter
@NoArgsConstructor
public class Resident {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "resident_serial_number", nullable = false)
    private Long residentSerialNumber;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "resident_registration_number", nullable = false, length = 300)
    private String residentRegistrationNumber;

    @Column(name = "gender_code", nullable = false, length = 20)
    private String genderCode;

    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @Column(name = "birth_place_code", nullable = false, length = 20)
    private String birthPlaceCode;

    @Column(name = "registration_base_address", nullable = false, length = 500)
    private String registrationBaseAddress;

    @Column(name = "death_date")
    private LocalDate deathDate;

    @Column(name = "death_place_code", length = 20)
    private String deathPlaceCode;

    @Column(name = "death_place_address", length = 500)
    private String deathPlaceAddress;

    public Resident(Long residentSerialNumber, String name, String residentRegistrationNumber, String genderCode, LocalDate birthDate, String birthPlaceCode, String registrationBaseAddress) {
        this.residentSerialNumber = residentSerialNumber;
        this.name = name;
        this.residentRegistrationNumber = residentRegistrationNumber;
        this.genderCode = genderCode;
        this.birthDate = birthDate;
        this.birthPlaceCode = birthPlaceCode;
        this.registrationBaseAddress = registrationBaseAddress;
    }
}