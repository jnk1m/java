package com.academy.certificate.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "household_composition_resident")
public class HouseholdCompositionResident {
    @EmbeddedId
    private Pk pk;

    @MapsId("householdSerialNumber")
    @ManyToOne
    @JoinColumn(name = "household_serial_number", nullable = false)
    private Household houseHold;

    @MapsId("residentSerialNumber")
    @ManyToOne()
    @JoinColumn(name = "resident_serial_number", nullable = false, insertable = false, updatable = false)
    private Resident resident;

    @Column(name = "report_date", nullable = false)
    private LocalDate reportDate;

    @Column(name = "household_relationship_code", nullable = false, length = 20)
    private String householdRelationshipCode;

    @Column(name = "household_composition_change_reason_code", nullable = false, length = 20)
    private String householdCompositionChangeReasonCode;


    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode
    @Embeddable
    public class Pk implements Serializable {
        @Column(name = "household_serial_number", nullable = false)
        private Long householdSerialNumber;

        @Column(name = "resident_serial_number", nullable = false)
        private Long residentSerialNumber;
    }

}