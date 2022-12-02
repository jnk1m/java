package com.academy.certificate.entity;

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
    private String familyRelationshipCode;

    @MapsId("baseResidentSerialNumber")
    @ManyToOne
    @JoinColumn(name = "base_resident_serial_number", referencedColumnName = "resident_serial_number")
    private Resident resident;


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