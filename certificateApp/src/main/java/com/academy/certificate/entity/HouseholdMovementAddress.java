package com.academy.certificate.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "household_movement_address")
public class HouseholdMovementAddress {
    @EmbeddedId
    private Pk pk;

    @MapsId("householdSerialNumber")
    @ManyToOne(optional = false)
    @JoinColumn(name = "household_serial_number", nullable = false)
    private Household household;

    @Column(name = "house_movement_address", nullable = false, length = 500)
    private String houseMovementAddress;

    @Column(name = "last_address_yn", columnDefinition = "true", nullable = false)
    private Boolean lastAddressYn;

    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode
    @Embeddable
    public class Pk implements Serializable {
        @Column(name = "household_serial_number", nullable = false)
        private Long householdSerialNumber;

        @Column(name = "house_movement_report_date", nullable = false)
        private LocalDate houseMovementReportDate;
    }

}