package com.academy.finalproject.service;

import com.academy.finalproject.entity.DepartmentMember;
import com.academy.finalproject.repository.DeptMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DeptMemberServiceImpl implements DeptMemberService {
    private final DeptMemberRepository deptMemberRepository;

//    @Override
//    public List<DeptMemberResponse> getDeptAndMember(String departmentId) {
//        return deptMemberRepository.selectDeptMemberById(departmentId);
//    }

    @Override
    public List<DepartmentMember> getDeptAndMember(String id) {
        return deptMemberRepository.findByPk_DepartmentId(id);
    }
}
