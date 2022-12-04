package com.academy.certificate.repository;

import com.academy.certificate.domain.BirthCertification;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface BirthDeathReportResidentRepositoryCustom {
    List<BirthCertification> getBirthCertificationBySerialNumber(Long serialNumber);
}
