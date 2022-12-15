package com.academy.finalproject.controller;

import com.academy.finalproject.domain.DeptMemberResponse;
import com.academy.finalproject.service.DeptMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class DeptMemberController {
    private final DeptMemberService deptMemberService;

    /*Accept 헤더를 인자로 받는지 체크하고 받지 않는다면 400 Bad Request를 응답합니다.*/
    @GetMapping("/department-members")
    public ResponseEntity<List<DeptMemberResponse>> getDeptMember(@RequestParam String departmentId,
                                                                  @RequestHeader(HttpHeaders.ACCEPT) String header) {

        /*departmentId 값을 넣지 않은 경우 400 Bad Request를 응답합니다*/
        if (departmentId == null || departmentId.equals("")) {
            return ResponseEntity.badRequest().build();
        }

        /*Accept 헤더가 application/json가 아니라면 400 Bad Request를 응답합니다. */
        if (!header.equals("application/json")) {
            return ResponseEntity.badRequest().build();
        }

        List<DeptMemberResponse> deptAndMember = deptMemberService.getDeptAndMember(departmentId);

        /*departmentId로 가져온 값이 없다면 200 Ok와 empty body를 응답합니다.*/
        if (deptAndMember.size() == 0) {
            return ResponseEntity.ok(null);
        }

        return ResponseEntity.ok(deptAndMember);

    }

}
