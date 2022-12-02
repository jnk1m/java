package com.academy.certificate.dto;

import com.academy.certificate.entity.FamilyRelationship;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * A DTO for the {@link FamilyRelationship} entity
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class FamilyRelationshipDto{
    @NotNull
    private Long familyResidentSerialNumber;

    @NotNull
    private String familyRelationshipCode;
}