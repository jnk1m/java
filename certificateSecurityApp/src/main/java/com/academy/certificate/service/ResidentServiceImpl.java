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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ResidentServiceImpl implements ResidentService {
    private final ResidentRepository residentRepository;
    private final PasswordEncoder passwordEncoder;

    public ResidentServiceImpl(ResidentRepository residentRepository, PasswordEncoder passwordEncoder) {
        this.residentRepository = residentRepository;
        this.passwordEncoder = passwordEncoder;
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

    @Override
    public Resident getResidentByUserId(String userId) throws ResidentNotFoundException {
        return residentRepository.findByUserId(userId).orElseThrow(ResidentNotFoundException::new);
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
            /*?????????????????? ??? 7?????? ????????? ????????? resident ????????? ????????? ?????? ?????? ????????????????????? ?????? ??????????????????.*/
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

    /*SecurityContextHolder?????? ????????? ID??? ???????????? ?????????*/
    @Override
    public String getUserIdFromSecurityContextHolder() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return userDetails.getUsername();
    }

    /*???????????? ????????? ?????? ????????? ???????????? ?????????*/
    @Override
    public List<ToBeResidentList> getHouseholdCompositionResidents(Long householdSerialNumber) {
        return residentRepository.getCompositionResidentList(householdSerialNumber);
    }

    /*OAuth ???????????? ?????? ???????????? ????????? ???????????? ?????????*/
    @Override
    public User getResidentByEmail(String email) {
        Resident resident = residentRepository.findByEmail(email).orElseThrow(ResidentNotFoundException::new);
        return new User(resident.getUsername(), resident.getPassword(), Collections.singletonList(new SimpleGrantedAuthority("USER")));
    }

    /*???????????? ????????? ???????????? ???????????? ?????????*/
    @Override
    public Long getHouseholdSerialNumber(Long residentSerialNumber) {
        return residentRepository.getHouseholdSerialNumber(residentSerialNumber);
    }


    /*?????? ?????? ??? ????????? ?????? ?????? ????????? ??????*/
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
            if (Objects.equals(dto.getGenderCode().getValue(), "???")) {
                registrationNum.append("1");
            }
        }
        if (splitRegistrationNum[0].startsWith("1")) {
            if (Objects.equals(dto.getGenderCode().getValue(), "???")) {
                registrationNum.append("2");
            }
        }
        if (splitRegistrationNum[0].startsWith("2")) {
            if (Objects.equals(dto.getGenderCode().getValue(), "???")) {
                registrationNum.append("3");
            }
        }
        if (splitRegistrationNum[0].startsWith("2")) {
            if (Objects.equals(dto.getGenderCode().getValue(), "???")) {
                registrationNum.append("4");
            }
        }
    }


}
