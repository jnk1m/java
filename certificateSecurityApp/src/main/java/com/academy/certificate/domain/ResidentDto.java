package com.academy.certificate.domain;

import com.academy.certificate.enums.BirthPlaceCode;
import com.academy.certificate.enums.GenderCode;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;
import java.util.Collection;


@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ResidentDto {
    private String name;

    @Enumerated(EnumType.STRING)
    private GenderCode genderCode;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime birthDate;

    @Enumerated(EnumType.STRING)
    private BirthPlaceCode birthPlaceCode;

    private String registrationBaseAddress;

    private String userId;

    private String password;

    private String email;

}