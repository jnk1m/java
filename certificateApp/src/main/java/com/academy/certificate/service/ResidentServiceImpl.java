package com.academy.certificate.service;

import com.academy.certificate.dto.ResidentDto;
import com.academy.certificate.dto.ModifyResidentDto;
import com.academy.certificate.entity.Resident;
import com.academy.certificate.enums.GenderCode;
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
    public Resident registerResident(ResidentDto residentDto) {
        Resident resident = new Resident(residentDto.getName(), residentDto.getResidentRegistrationNumber(),
                residentDto.getGenderCode(), residentDto.getBirthDate(),
                residentDto.getBirthPlaceCode(), residentDto.getRegistrationBaseAddress());

        return residentRepository.save(resident);
    }

    @Transactional
    @Override
    public int modifyResidentInfo(Long serialNumber, ModifyResidentDto modifyResidentDto) throws ResidentNotFoundException {
        checkExistResident(serialNumber);

        String name = modifyResidentDto.getName();
        String residentRegistrationNumber = modifyResidentDto.getResidentRegistrationNumber();
        GenderCode genderCode = modifyResidentDto.getGenderCode();
        String registrationBaseAddress = modifyResidentDto.getRegistrationBaseAddress();

        int modifyResult = 0;

        if (name != null) {
            modifyResult = residentRepository.modifyName(name, serialNumber);
        }

        if (residentRegistrationNumber != null) {
            /*주민등록번호 뒤 7자리 수정을 위하여 resident 객체를 가지고 와서 기존 주민등록번호를 잘라 연결해줍니다.*/
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
