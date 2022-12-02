package com.academy.certificate.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "certificate_issue")
public class CertificateIssue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "certificate_confirmation_number", nullable = false)
    private Long certificateConfirmationNumber;

    @ManyToOne(optional = false)
    @JoinColumn(name = "resident_serial_number", nullable = false)
    private Resident resident;

    @Column(name = "certificate_type_code", nullable = false, length = 20)
    private String certificateTypeCode;

    @Column(name = "certificate_issue_date", nullable = false)
    private LocalDate certificateIssueDate;

}