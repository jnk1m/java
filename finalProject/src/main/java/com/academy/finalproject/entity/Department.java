package com.academy.finalproject.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Department {
    @Id
    @Column(name = "department_id", nullable = false, length = 20)
    private String departmentId;

    @Column(name = "name", nullable = false, length = 30)
    private String name;
}
