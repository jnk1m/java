package com.academy.finalproject.service;

import com.academy.finalproject.domain.DeptMemberResponse;

import java.util.List;

public interface DeptMemberService {
    List<DeptMemberResponse> getDeptAndMember(String departmentId);
}
