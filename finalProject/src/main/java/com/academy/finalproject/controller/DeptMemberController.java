package com.academy.finalproject.controller;

import com.academy.finalproject.domain.DeptMemberDto;
import com.academy.finalproject.entity.DepartmentMember;
import com.academy.finalproject.service.DeptMemberService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class DeptMemberController {
    private final DeptMemberService deptMemberService;
    private final ModelMapper modelMapper;

    /*Accept 헤더를 인자로 받는지 체크하고 받지 않는다면 400 Bad Request를 응답합니다.*/
    @GetMapping("/department-members")
    public ResponseEntity<List<DeptMemberDto>> getDeptMember(@RequestParam String departmentId,
                                                             @RequestHeader(HttpHeaders.ACCEPT) String header) {

        /*departmentId 값을 넣지 않은 경우 400 Bad Request를 응답합니다*/
        if (departmentId == null || departmentId.equals("")) {
            return ResponseEntity.badRequest().build();
        }

        /*Accept 헤더가 application/json가 아니라면 400 Bad Request를 응답합니다. */
        if (!header.equals("application/json")) {
            return ResponseEntity.badRequest().build();
        }

        List<DepartmentMember> entityDepartmentMember = deptMemberService.getDeptAndMember(departmentId);

        /*departmentId로 가져온 값이 없다면 200 Ok와 empty body를 응답합니다.*/
        if (entityDepartmentMember.size() == 0) {
            return ResponseEntity.ok(null);
        }

        List<DeptMemberDto> DeptMemberDtoList = entityDepartmentMember.stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok(DeptMemberDtoList);
    }

    public DeptMemberDto convertEntityToDto(DepartmentMember customer) {
        return modelMapper.map(customer, DeptMemberDto.class);
    }


}
