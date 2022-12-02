package com.academy.certificate.service;

import com.academy.certificate.dto.FamilyRelationshipDto;
import com.academy.certificate.entity.FamilyRelationship;
import com.academy.certificate.entity.Resident;
import com.academy.certificate.repository.FamilyRelationshipRepository;
import com.academy.certificate.repository.ResidentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class FamilyRelationshipServiceImpl implements FamilyRelationshipService {
    private FamilyRelationshipRepository familyRelationshipRepository;
    private ResidentRepository residentRepository;

    public FamilyRelationshipServiceImpl(FamilyRelationshipRepository familyRelationshipRepository, ResidentRepository residentRepository) {
        this.familyRelationshipRepository = familyRelationshipRepository;
        this.residentRepository = residentRepository;
    }

    @Override
    @Transactional
    public FamilyRelationship registerFamilyRelationship(Long serialNumber, FamilyRelationshipDto familyRelationshipDto) {
        Optional<Resident> resident = residentRepository.findById(serialNumber);
        FamilyRelationship family = new FamilyRelationship(new FamilyRelationship.Pk(serialNumber, familyRelationshipDto.getFamilyResidentSerialNumber()),
                familyRelationshipDto.getFamilyRelationshipCode(), resident.get());


        return familyRelationshipRepository.save(family);
    }
}

