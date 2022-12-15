package com.academy.finalproject.service;

import com.academy.finalproject.entity.DepartmentMember;

import java.util.List;

public interface DeptMemberService {
//    List<DeptMemberResponse> getDeptAndMember(String departmentId);

    List<DepartmentMember> getDeptAndMember(String id);
}
