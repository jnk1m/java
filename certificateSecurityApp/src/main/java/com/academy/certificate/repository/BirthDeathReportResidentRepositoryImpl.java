package com.academy.certificate.repository;

import com.academy.certificate.domain.BirthCertification;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class BirthDeathReportResidentRepositoryImpl extends QuerydslRepositorySupport implements BirthDeathReportResidentRepositoryCustom {
    public BirthDeathReportResidentRepositoryImpl() {
        super(BirthCertification.class);
    }

    @Override
    public List<BirthCertification> getBirthCertificationBySerialNumber(Long serialNumber) {
        return null;
    }

    /*SELECT r.name, r.gender_code, r.birth_date, r.birth_place_code, r.registration_base_address,
    b.birth_death_report_date, b.report_resident_serial_number, b.birth_report_qualifications_code,
    b.email_address, b.phone_number
    FROM birth_death_report_resident b
    INNER JOIN resident r on r.resident_serial_number = b.resident_serial_number
    WHERE b.birth_death_type_code = "출생" AND b.resident_serial_number = 7;*/


}
