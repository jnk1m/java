package com.academy.certificate.repository;

import com.academy.certificate.domain.BirthCertification;
import com.academy.certificate.entity.BirthDeathReportResident;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BirthDeathReportResidentRepository extends JpaRepository<BirthDeathReportResident, BirthDeathReportResident.Pk>, BirthDeathReportResidentRepositoryCustom {

}