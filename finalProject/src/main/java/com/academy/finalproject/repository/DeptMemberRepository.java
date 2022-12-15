package com.academy.finalproject.repository;

import com.academy.finalproject.entity.DepartmentMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeptMemberRepository extends JpaRepository<DepartmentMember, String> {

    List<DepartmentMember> findByPk_DepartmentId(String id);


}
