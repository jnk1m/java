package com.academy.security.controller;

import com.academy.security.domain.MemberCreateRequest;
import com.academy.security.domain.MemberDto;
import com.academy.security.domain.MemberId;
import com.academy.security.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/members")
public class MemberRestController {
    private final MemberService memberService;

    public MemberRestController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping
    public MemberId createMember(@RequestBody MemberCreateRequest request) {
        return memberService.createMember(request);
    }


    @GetMapping("/{memberId}")
    public MemberDto getMember(@PathVariable("memberId") String memberId){
        return memberService.getMember(memberId);
    }

}
