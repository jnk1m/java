package com.academy.certificate.entity;

import com.academy.certificate.enums.FamilyRelationshipCode;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "family_relationship")
@AllArgsConstructor
public class FamilyRelationship {

    @EmbeddedId
    private Pk pk;

    @Column(name = "family_relationship_code")
    @Enumerated(EnumType.STRING)
    private FamilyRelationshipCode familyRelationshipCode;

    @MapsId("baseResidentSerialNumber")
    @ManyToOne
    @JoinColumn(name = "base_resident_serial_number", referencedColumnName = "resident_serial_number")
    private Resident resident;

    public FamilyRelationship(FamilyRelationshipCode familyRelationshipCode, Resident resident) {
        this.familyRelationshipCode = familyRelationshipCode;
        this.resident = resident;
    }


    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode
    @Getter
    @Embeddable
    public static class Pk implements Serializable {
        @Column(name = "base_resident_serial_number")
        private Long baseResidentSerialNumber;

        @Column(name = "family_resident_serial_number")
        private Long familyResidentSerialNumber;

    }

}