package com.academy.certificate.repository;

import com.academy.certificate.entity.Resident;
import com.academy.certificate.enums.GenderCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface ResidentRepository extends JpaRepository<Resident, Long> {
    
    @Modifying
    @Transactional
    @Query("update Resident r set r.name = ?1 where r.residentSerialNumber = ?2 ")
    int modifyName(@Param("name")String name, @Param("serialNumber") Long serialNumber);

    @Modifying
    @Transactional
    @Query("update Resident r set r.residentRegistrationNumber = ?1 where r.residentSerialNumber = ?2 ")
    int modifyResidentRegistrationNumber(String modifiedResidentRegistrationNumber, Long serialNumber);

    @Modifying
    @Transactional
    @Query("update Resident r set r.genderCode = ?1 where r.residentSerialNumber = ?2 ")
    int modifyGenderCode(GenderCode genderCode, Long serialNumber);

    @Modifying
    @Transactional
    @Query("update Resident r set r.registrationBaseAddress = ?1 where r.residentSerialNumber = ?2 ")
    int modifyRegistrationBaseAddress(String registrationBaseAddress, Long serialNumber);
}
