package com.academy.certificate.service;

import com.academy.certificate.domain.FamilyRelationshipDto;
import com.academy.certificate.domain.ModifyFamilyRelationshipDto;
import com.academy.certificate.entity.FamilyRelationship;
import com.academy.certificate.entity.Resident;
import com.academy.certificate.exception.FamilyRelationshipNotFoundException;
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
        FamilyRelationship family = new FamilyRelationship(
                dto.getFamilyRelationshipCode(), resident.get());
//        FamilyRelationship family = new FamilyRelationship(new FamilyRelationship.Pk(serialNumber, dto.getFamilyResidentSerialNumber()),
//                dto.getFamilyRelationshipCode(), resident.get());

        FamilyRelationship save = familyRelationshipRepository.save(family);
        return save;
    }

    @Override
    @Transactional
    public int modifyFamilyRelationship(Long serialNumber, Long familySerialNumber, ModifyFamilyRelationshipDto dto) throws FamilyRelationshipNotFoundException {
        familyRelationshipRepository.findById(new FamilyRelationship.Pk(serialNumber, familySerialNumber)).orElseThrow(FamilyRelationshipNotFoundException::new);
        return familyRelationshipRepository.updateFamilyRelationshipCode(serialNumber, familySerialNumber, dto.getFamilyRelationshipCode());
    }

    @Override
    public int deleteFamilyRelationship(Long serialNumber, Long familySerialNumber) throws FamilyRelationshipNotFoundException {
        familyRelationshipRepository.findById(new FamilyRelationship.Pk(serialNumber, familySerialNumber)).orElseThrow(FamilyRelationshipNotFoundException::new);

        return familyRelationshipRepository.deleteFamilyRelationship(serialNumber, familySerialNumber);
    }
}

