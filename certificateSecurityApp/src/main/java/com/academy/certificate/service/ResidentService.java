package com.academy.certificate.service;

import com.academy.certificate.domain.ResidentDto;
import com.academy.certificate.domain.ModifyResidentDto;
import com.academy.certificate.domain.ToBeResidentList;
import com.academy.certificate.entity.Resident;
import com.academy.certificate.exception.ResidentNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ResidentService {

    void checkExistResident(Long serialNumber)throws ResidentNotFoundException;
    Optional<Resident> getResident(Long serialNumber) throws ResidentNotFoundException;

    Resident registerResident(ResidentDto residentDto);

    int modifyResidentInfo(Long serialNumber, ModifyResidentDto modifyResidentDto) throws ResidentNotFoundException;

    Page<ToBeResidentList> getAllResidents(Pageable pageable);
}
