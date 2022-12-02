package com.academy.certificate.service;

import com.academy.certificate.dto.ModifyFamilyRelationshipDto;
import com.academy.certificate.dto.ResidentDto;
import com.academy.certificate.dto.ModifyResidentDto;
import com.academy.certificate.entity.Resident;
import com.academy.certificate.exception.ResidentNotFoundException;

import java.util.Optional;

public interface ResidentService {

    void checkExistResident(Long serialNumber)throws ResidentNotFoundException;
    Optional<Resident> getResident(Long serialNumber) throws ResidentNotFoundException;

    Resident registerResident(ResidentDto residentDto);


    int modifyResidentInfo(Long serialNumber, ModifyResidentDto modifyResidentDto) throws ResidentNotFoundException;

}
