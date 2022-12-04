package com.academy.certificate.domain;

import com.academy.certificate.enums.FamilyRelationshipCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;


@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ModifyFamilyRelationshipDto {
    @NotNull
    @Enumerated(EnumType.STRING)
    private FamilyRelationshipCode familyRelationshipCode;
}