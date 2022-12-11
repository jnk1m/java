package com.academy.certificate.entity;

import com.academy.certificate.enums.BirthPlaceCode;
import com.academy.certificate.enums.DeathPlaceCode;
import com.academy.certificate.enums.GenderCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;

@Entity
@Table(name = "resident")
@Getter
@NoArgsConstructor
public class Resident implements UserDetails {
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


    @Column(name = "id")
    private String userId;

    private String email;

    @Column(name = "password")
    private String pwd;


    public Resident(String name, String residentRegistrationNumber,
                    GenderCode genderCode, LocalDateTime birthDate, BirthPlaceCode birthPlaceCode,
                    String registrationBaseAddress, String userId, String email, String pwd) {
        this.name = name;
        this.residentRegistrationNumber = residentRegistrationNumber;
        this.genderCode = genderCode;
        this.birthDate = birthDate;
        this.birthPlaceCode = birthPlaceCode;
        this.registrationBaseAddress = registrationBaseAddress;
        this.userId = userId;
        this.email = email;
        this.pwd = pwd;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.pwd;
    }

    @Override
    public String getUsername() {
        return this.userId;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


}