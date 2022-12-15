package com.academy.finalproject.repository;

import com.academy.finalproject.domain.DeptMemberResponse;
import com.academy.finalproject.entity.DepartmentMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DeptMemberRepository extends JpaRepository<DepartmentMember, String> {

    @Query("SELECT d.departmentId as departmentId, d.name as departmentName, " +
            "e.employeeId as employeeId, e.name as employeeName " +
            "FROM DepartmentMember dm " +
            "INNER JOIN Department d on d.departmentId = dm.pk.departmentId " +
            "INNER JOIN Employee e on e.employeeId = dm.pk.employeeId " +
            "WHERE dm.department.departmentId = ?1")
    List<DeptMemberResponse> selectDeptMemberById(String departmentId);


}
