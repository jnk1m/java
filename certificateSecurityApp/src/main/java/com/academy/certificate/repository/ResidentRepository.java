package com.academy.certificate.repository;

import com.academy.certificate.domain.ToBeResidentList;
import com.academy.certificate.entity.Resident;
import com.academy.certificate.enums.GenderCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface ResidentRepository extends JpaRepository<Resident, Long>, ResidentRepositoryCustom {

    Optional<Resident> findByUserId(String userId);


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

    Page<ToBeResidentList> getAllBy(Pageable pageable);


//    @Query("SELECT r.residentSerialNumber, r.name, r.genderCode " +
//            "from Resident as r " +
//            "INNER JOIN HouseholdCompositionResident hc on hc.pk.residentSerialNumber = r.residentSerialNumber " +
//            "WHERE hc.pk.householdSerialNumber= ?1")
//    List<ToBeResidentList> getCompositionResidentList(Long residentSerialNumber);

    @Query("SELECT r.residentSerialNumber, r.name, r.genderCode " +
            "from Resident as r " +
            "INNER JOIN HouseholdCompositionResident hc on hc.pk.residentSerialNumber = r.residentSerialNumber " +
            "WHERE hc.pk.householdSerialNumber= ?1")
    List<ToBeResidentList> getCompositionResidentList(Long residentSerialNumber);

}
