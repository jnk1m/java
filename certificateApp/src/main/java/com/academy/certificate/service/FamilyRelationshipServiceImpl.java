package com.academy.certificate.service;

import com.academy.certificate.dto.FamilyRelationshipDto;
import com.academy.certificate.dto.ModifyFamilyRelationshipDto;
import com.academy.certificate.entity.FamilyRelationship;
import com.academy.certificate.entity.Resident;
import com.academy.certificate.repository.FamilyRelationshipRepository;
import com.academy.certificate.repository.ResidentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class FamilyRelationshipServiceImpl implements FamilyRelationshipService {
    private final FamilyRelationshipRepository familyRelationshipRepository;
    private final ResidentRepository residentRepository;

    public FamilyRelationshipServiceImpl(FamilyRelationshipRepository familyRelationshipRepository, ResidentRepository residentRepository) {
        this.familyRelationshipRepository = familyRelationshipRepository;
        this.residentRepository = residentRepository;
    }

    @Override
    @Transactional
    public FamilyRelationship registerFamilyRelationship(Long serialNumber, FamilyRelationshipDto dto) {
        Optional<Resident> resident = residentRepository.findById(serialNumber);
        FamilyRelationship family = new FamilyRelationship(new FamilyRelationship.Pk(serialNumber, dto.getFamilyResidentSerialNumber()),
                dto.getFamilyRelationshipCode(), resident.get());


        return familyRelationshipRepository.save(family);
    }

    @Override
    @Transactional
    public int modifyFamilyRelationship(Long serialNumber, Long familySerialNumber, ModifyFamilyRelationshipDto dto) {
        return familyRelationshipRepository.updateFamilyRelationshipCode(serialNumber, familySerialNumber, dto.getFamilyRelationshipCode());
    }
}

