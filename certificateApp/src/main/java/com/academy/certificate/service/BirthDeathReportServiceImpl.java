package com.academy.certificate.service;

import com.academy.certificate.domain.BirthReportDto;
import com.academy.certificate.entity.BirthDeathReportResident;
import com.academy.certificate.entity.Resident;
import com.academy.certificate.repository.BirthDeathReportResidentRepository;
import com.academy.certificate.repository.ResidentRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BirthDeathReportServiceImpl implements BirthDeathReportService {
    private final ResidentRepository residentRepository;
    private final BirthDeathReportResidentRepository birthDeathReportResidentRepository;

    public BirthDeathReportServiceImpl(ResidentRepository residentRepository,
                                       BirthDeathReportResidentRepository birthDeathReportResidentRepository) {
        this.residentRepository = residentRepository;
        this.birthDeathReportResidentRepository = birthDeathReportResidentRepository;
    }

    @Override
    public BirthReportDto registerBirth(Long birthSerialNumber, Long serialNumber, BirthReportDto dto) {
        Optional<Resident> resident = residentRepository.findById(serialNumber);
        BirthDeathReportResident.Pk pk = new BirthDeathReportResident.Pk(dto.getBirthDeathTypeCode(),birthSerialNumber);
        BirthDeathReportResident birthReport = new BirthDeathReportResident(pk, resident.get(),
                dto.getBirthDeathReportDate(), dto.getBirthReportQualificationsCode(), dto.getEmailAddress(),
                dto.getPhoneNumber());

        birthDeathReportResidentRepository.save(birthReport);
        return dto;

    }
}
