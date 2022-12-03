package com.academy.certificate.entity;

import com.academy.certificate.enums.BirthPlaceCode;
import com.academy.certificate.enums.DeathPlaceCode;
import com.academy.certificate.enums.GenderCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "resident")
@Getter
@NoArgsConstructor
public class Resident {
    @Id
    @Column(name = "resident_serial_number")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long residentSerialNumber;

    @Column(name = "name", length = 100)
    private String name;

    @Column(name = "resident_registration_number", length = 300)
    private String residentRegistrationNumber;

    @Column(name = "gender_code", length = 20)
    @Enumerated(EnumType.STRING)
    private GenderCode genderCode;

    @Column(name = "birth_date")
    private LocalDateTime birthDate;

    @Column(name = "birth_place_code", length = 20)
    @Enumerated(EnumType.STRING)
    private BirthPlaceCode birthPlaceCode;

    @Column(name = "registration_base_address", length = 500)
    private String registrationBaseAddress;

    @Column(name = "death_date")
    private LocalDateTime deathDate;

    @Column(name = "death_place_code", length = 20)
    @Enumerated(EnumType.STRING)
    private DeathPlaceCode deathPlaceCode;

    @Column(name = "death_place_address", length = 500)
    private String deathPlaceAddress;

    public Resident(String name, String residentRegistrationNumber,
                    GenderCode genderCode, LocalDateTime birthDate, BirthPlaceCode birthPlaceCode,
                    String registrationBaseAddress) {
        this.name = name;
        this.residentRegistrationNumber = residentRegistrationNumber;
        this.genderCode = genderCode;
        this.birthDate = birthDate;
        this.birthPlaceCode = birthPlaceCode;
        this.registrationBaseAddress = registrationBaseAddress;
    }

}