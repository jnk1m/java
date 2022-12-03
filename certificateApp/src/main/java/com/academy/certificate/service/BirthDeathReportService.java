package com.academy.certificate.service;

import com.academy.certificate.dto.BirthReportDto;
import com.academy.certificate.entity.BirthDeathReportResident;
import com.academy.certificate.entity.Resident;

import java.util.Optional;

public interface BirthDeathReportService {
    BirthReportDto registerBirth(Long birthSerialNumber, Long serialNumber, BirthReportDto dto);
}
