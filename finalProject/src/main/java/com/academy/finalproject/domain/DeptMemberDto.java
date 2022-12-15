package com.academy.finalproject.domain;

import com.academy.finalproject.entity.Department;
import com.academy.finalproject.entity.DepartmentMember;
import com.academy.finalproject.entity.Employee;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DeptMemberDto {
    Department department;

    Employee employee;


}
