package com.academy.certificate.domain;

import com.academy.certificate.enums.GenderCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Size;


@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ModifyResidentDto {
    private String name;

    /*주민등록번호 수정은 뒷자리만 가능하여 Size(max=7)로 설정*/
    @Size(max = 7)
    private String residentRegistrationNumber;

    @Enumerated(EnumType.STRING)
    private GenderCode genderCode;

    private String registrationBaseAddress;

}


