package com.academy.certificate.repository;

import com.academy.certificate.entity.FamilyRelationship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface FamilyRelationshipRepository extends JpaRepository<FamilyRelationship, FamilyRelationship.Pk> {

    @Modifying
    @Transactional
    @Query("UPDATE FamilyRelationship f set f.familyRelationshipCode = ?3 WHERE f.pk.baseResidentSerialNumber = ?1 AND f.pk.familyResidentSerialNumber = ?2")
    int updateFamilyRelationshipCode(@Param("serialNumber") Long serialNumber,
                                     @Param("familySerialNumber") Long familySerialNumber,
                                     @Param("familyRelationshipCode") String familyRelationshipCode);

}