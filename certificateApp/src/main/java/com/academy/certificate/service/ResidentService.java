package com.academy.certificate.service;

import com.academy.certificate.dto.AliveResidentDto;
import com.academy.certificate.dto.EditResidentDto;
import com.academy.certificate.entity.Resident;
import com.academy.certificate.exception.ResidentNotFoundException;

import java.util.Optional;

public interface ResidentService {

    void checkExistResident(Long serialNumber)throws ResidentNotFoundException;
    Optional<Resident> getResident(Long serialNumber) throws ResidentNotFoundException;

    Resident registerResident(AliveResidentDto aliveResidentDto);


    int modifyResidentInfo(Long serialNumber, EditResidentDto editResidentDto) throws ResidentNotFoundException;
}
