package com.academy.certificate.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

/**
 * A DTO for the {@link com.academy.certificate.entity.Resident} entity
 */

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ModifyResidentDto {
    private String name;

    /*주민등록번호 수정은 뒷자리만 가능하여 Size(max=7)로 설정*/
    @Size(max = 7)
    private String residentRegistrationNumber;

    private String genderCode;

    private String registrationBaseAddress;

}


