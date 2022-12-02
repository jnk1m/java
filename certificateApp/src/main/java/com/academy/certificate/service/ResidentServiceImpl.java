package com.academy.certificate.service;

import com.academy.certificate.dto.AliveResidentDto;
import com.academy.certificate.dto.EditResidentDto;
import com.academy.certificate.entity.Resident;
import com.academy.certificate.exception.ResidentNotFoundException;
import com.academy.certificate.repository.ResidentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ResidentServiceImpl implements ResidentService {
    private final ResidentRepository residentRepository;

    public ResidentServiceImpl(ResidentRepository residentRepository) {
        this.residentRepository = residentRepository;
    }

    @Override
    public void checkExistResident(Long serialNumber) throws ResidentNotFoundException {
        boolean isExist = residentRepository.existsById(serialNumber);
        if (!isExist) {
            throw new ResidentNotFoundException();
        }
    }

    @Override
    public Optional<Resident> getResident(Long serialNumber) throws ResidentNotFoundException {
        checkExistResident(serialNumber);

        return residentRepository.findById(serialNumber);
    }

    @Transactional
    @Override
    public Resident registerResident(AliveResidentDto aliveResidentDto) {
        Resident resident = new Resident(aliveResidentDto.getResidentSerialNumber(), aliveResidentDto.getName(), aliveResidentDto.getResidentRegistrationNumber(),
                aliveResidentDto.getGenderCode(), aliveResidentDto.getBirthDate(),
                aliveResidentDto.getBirthPlaceCode(), aliveResidentDto.getRegistrationBaseAddress());

        return residentRepository.save(resident);
    }

    @Transactional
    @Override
    public int modifyResidentInfo(Long serialNumber, EditResidentDto editResidentDto) throws ResidentNotFoundException {
        checkExistResident(serialNumber);

        String name = editResidentDto.getName();
        String residentRegistrationNumber = editResidentDto.getResidentRegistrationNumber();
        String genderCode = editResidentDto.getGenderCode();
        String registrationBaseAddress = editResidentDto.getRegistrationBaseAddress();

        int modifyResult = 0;

        if (name != null) {
            modifyResult = residentRepository.modifyName(name, serialNumber);
        }

        if (residentRegistrationNumber != null) {
            Optional<Resident> resident = residentRepository.findById(serialNumber);
            String originalResidentRegistrationNumber = resident.get().getResidentRegistrationNumber().substring(0, 7);
            String modifiedResidentRegistrationNumber = originalResidentRegistrationNumber.concat(residentRegistrationNumber);

            modifyResult = residentRepository.modifyResidentRegistrationNumber(modifiedResidentRegistrationNumber, serialNumber);
        }

        if (genderCode != null) {
            modifyResult = residentRepository.modifyGenderCode(genderCode, serialNumber);
        }

        if (registrationBaseAddress != null) {
            modifyResult = residentRepository.modifyRegistrationBaseAddress(registrationBaseAddress, serialNumber);
        }

        return modifyResult;
    }


}
