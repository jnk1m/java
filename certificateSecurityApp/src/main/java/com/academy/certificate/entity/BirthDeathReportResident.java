package com.academy.certificate.entity;

import com.academy.certificate.enums.BirthDeathTypeCode;
import com.academy.certificate.enums.BirthReportQualificationsCode;
import com.academy.certificate.enums.DeathReportQualificationsCode;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "birth_death_report_resident")
public class BirthDeathReportResident {
    @EmbeddedId
    private Pk pk;

    @ManyToOne
    @JoinColumn(name = "report_resident_serial_number", referencedColumnName = "resident_serial_number")
    private Resident reportResidentSerialNumber;

    @Column(name = "birth_death_report_date")
    private LocalDate birthDeathReportDate;

    @Column(name = "birth_report_qualifications_code", length = 20)
    @Enumerated(EnumType.STRING)
    private BirthReportQualificationsCode birthReportQualificationsCode;

    @Column(name = "death_report_qualifications_code", length = 20)
    @Enumerated(EnumType.STRING)
    private DeathReportQualificationsCode deathReportQualificationsCode;

    @Column(name = "email_address", length = 50)
    private String emailAddress;

    @Column(name = "phone_number", length = 10)
    private String phoneNumber;

    /*출생 신고*/
    public BirthDeathReportResident(Pk pk, Resident reportResidentSerialNumber, LocalDate birthDeathReportDate,
                                    BirthReportQualificationsCode birthReportQualificationsCode, String emailAddress, String phoneNumber) {
        this.pk = pk;
        this.reportResidentSerialNumber = reportResidentSerialNumber;
        this.birthDeathReportDate = birthDeathReportDate;
        this.birthReportQualificationsCode = birthReportQualificationsCode;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
    }

    /*사망 신고*/
    public BirthDeathReportResident(Pk pk, Resident reportResidentSerialNumber, LocalDate birthDeathReportDate, DeathReportQualificationsCode deathReportQualificationsCode, String phoneNumber) {
        this.pk = pk;
        this.reportResidentSerialNumber = reportResidentSerialNumber;
        this.birthDeathReportDate = birthDeathReportDate;
        this.deathReportQualificationsCode = deathReportQualificationsCode;
        this.phoneNumber = phoneNumber;
    }


    @NoArgsConstructor
    @EqualsAndHashCode
    @Embeddable
    public static class Pk implements Serializable {
        @Column(name = "birth_death_type_code", length = 20)
        @Enumerated(EnumType.STRING)
        private BirthDeathTypeCode birthDeathTypeCode;

        @Column(name = "resident_serial_number")
        private Long residentSerialNumber;

        public Pk(BirthDeathTypeCode birthDeathTypeCode, Long residentSerialNumber) {
            this.birthDeathTypeCode = birthDeathTypeCode;
            this.residentSerialNumber = residentSerialNumber;
        }
    }
}