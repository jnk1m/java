package com.academy.certificate.service;

import com.academy.certificate.dto.FamilyRelationshipDto;
import com.academy.certificate.dto.ModifyFamilyRelationshipDto;
import com.academy.certificate.entity.FamilyRelationship;
import com.academy.certificate.exception.FamilyRelationshipNotFoundException;

public interface FamilyRelationshipService {
    FamilyRelationship registerFamilyRelationship(Long serialNumber, FamilyRelationshipDto familyRelationshipDto);

    int modifyFamilyRelationship(Long serialNumber, Long familySerialNumber, ModifyFamilyRelationshipDto dto) throws FamilyRelationshipNotFoundException;

    int deleteFamilyRelationship(Long serialNumber, Long familySerialNumber) throws FamilyRelationshipNotFoundException;
}
