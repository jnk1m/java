package com.academy.certificate.dto;

import com.academy.certificate.enums.FamilyRelationshipCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * A DTO for the {@link com.academy.certificate.entity.FamilyRelationship} entity
 */

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ModifyFamilyRelationshipDto{
    @NotNull
    @Enumerated(EnumType.STRING)
    private FamilyRelationshipCode familyRelationshipCode;
}