package com.academy.certificate.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "family_relationship")
public class FamilyRelationship {
    @EmbeddedId
    private Pk pk;

    @Column(name = "family_relationship_code", nullable = false)
    private String familyRelationshipCode;

    @MapsId("baseResidentSerialNumber")
    @ManyToOne(optional = false)
    @JoinColumn(name = "base_resident_serial_number", nullable = false)
    private Resident resident;


    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode
    @Embeddable
    public class Pk implements Serializable {
        @Column(name = "family_resident_serial_number")
        private Long familyResidentSerialNumber;

        @Column(name = "base_resident_serial_number")
        private Long baseResidentSerialNumber;
    }
}