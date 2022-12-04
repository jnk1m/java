package com.academy.certificate.service;

import com.academy.certificate.domain.BirthReportDto;

public interface BirthDeathReportService {
    BirthReportDto registerBirth(Long birthSerialNumber, Long serialNumber, BirthReportDto dto);
}
