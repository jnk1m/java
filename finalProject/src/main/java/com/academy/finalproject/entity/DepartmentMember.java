package com.academy.finalproject.entity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentMember {
    @EmbeddedId
    private Pk pk;

    @MapsId("departmentId")
    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @MapsId("employeeId")
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;


    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode
    @Embeddable
    public static class Pk implements Serializable {
        @Column(name = "department_id", nullable = false, length = 20)
        private String departmentId;

        @Column(name = "employee_id", nullable = false)
        private Long employeeId;

    }

}
