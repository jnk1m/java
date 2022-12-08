package com.academy.certificate.service;

import com.academy.certificate.domain.ModifyResidentDto;
import com.academy.certificate.domain.ResidentDto;
import com.academy.certificate.domain.ToBeResidentList;
import com.academy.certificate.entity.Resident;
import com.academy.certificate.enums.GenderCode;
import com.academy.certificate.exception.ResidentNotFoundException;
import com.academy.certificate.repository.ResidentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;

@Service
public class ResidentServiceImpl implements ResidentService {
    private final ResidentRepository residentRepository;
    private final PasswordEncoder passwordEncoder;
    private final RedisTemplate<String, Object> redisTemplate;

    public ResidentServiceImpl(ResidentRepository residentRepository, PasswordEncoder passwordEncoder, RedisTemplate<String, Object> redisTemplate) {
        this.residentRepository = residentRepository;
        this.passwordEncoder = passwordEncoder;
        this.redisTemplate = redisTemplate;
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
        String registrationNum = generateResidentRegistrationNum(residentDto);
        Resident resident = new Resident(residentDto.getName(), registrationNum,
                residentDto.getGenderCode(), residentDto.getBirthDate(),
                residentDto.getBirthPlaceCode(), residentDto.getRegistrationBaseAddress(),
                residentDto.getUserId(), residentDto.getEmail(), passwordEncoder.encode(residentDto.getPassword()));

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

    @Override
    public Page<ToBeResidentList> getAllResidents(Pageable pageable) {
        return residentRepository.getAllBy(pageable);
    }


    /*주민 등록 시 필요한 주민 등록 번호를 생성*/
    private String generateResidentRegistrationNum(ResidentDto dto) {
        String[] splitRegistrationNum = dto.getBirthDate().toString().split("-");
        StringBuilder registrationNum = new StringBuilder();
        registrationNum.append(splitRegistrationNum[0].substring(2))
                .append(splitRegistrationNum[1])
                .append(splitRegistrationNum[2].substring(0, 2))
                .append("-");

        generateGenderCode(dto, splitRegistrationNum, registrationNum);

        registrationNum.append("123456");

        return registrationNum.toString();

    }

    private void generateGenderCode(ResidentDto dto, String[] splitRegistrationNum, StringBuilder registrationNum) {
        if (splitRegistrationNum[0].startsWith("1")) {
            if (Objects.equals(dto.getGenderCode().getValue(), "남")) {
                registrationNum.append("1");
            }
        }
        if (splitRegistrationNum[0].startsWith("1")) {
            if (Objects.equals(dto.getGenderCode().getValue(), "여")) {
                registrationNum.append("2");
            }
        }
        if (splitRegistrationNum[0].startsWith("2")) {
            if (Objects.equals(dto.getGenderCode().getValue(), "남")) {
                registrationNum.append("3");
            }
        }
        if (splitRegistrationNum[0].startsWith("2")) {
            if (Objects.equals(dto.getGenderCode().getValue(), "여")) {
                registrationNum.append("4");
            }
        }
    }


}
