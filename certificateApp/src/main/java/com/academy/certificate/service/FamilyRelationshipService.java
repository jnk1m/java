package com.academy.certificate.service;

import com.academy.certificate.dto.FamilyRelationshipDto;
import com.academy.certificate.entity.FamilyRelationship;

public interface FamilyRelationshipService {
    FamilyRelationship registerFamilyRelationship(Long serialNumber, FamilyRelationshipDto familyRelationshipDto);
}
