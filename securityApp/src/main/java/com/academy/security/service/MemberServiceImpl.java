package com.academy.security.service;

import com.academy.security.domain.MemberCreateRequest;
import com.academy.security.domain.MemberDto;
import com.academy.security.domain.MemberId;
import com.academy.security.entity.Authority;
import com.academy.security.entity.Member;
import com.academy.security.exception.MemberNotFoundException;
import com.academy.security.repository.MemberRepository;
import com.academy.security.util.PasswordUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.util.Objects;

@Service
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public MemberServiceImpl(MemberRepository memberRepository,
                         PasswordEncoder passwordEncoder) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public MemberId createMember(MemberCreateRequest request) {
        Member member = new Member();
        member.setId(request.getId());
        member.setName(request.getName());
        member.setPwd(passwordEncoder.encode(request.getPwd()));

        Authority authority = new Authority();
        authority.setMember(member);
        authority.setAuthority(request.getAuthority());

        member.setAuthority(authority);

        memberRepository.saveAndFlush(member);

        MemberId memberId = new MemberId();
        memberId.setId(member.getId());

        return memberId;
    }

    @Override
    public MemberDto getMember(String id) {
        Member member = memberRepository.findById(id).orElseThrow(() -> new MemberNotFoundException(id));
        MemberDto memberDto = new MemberDto();
        memberDto.setId(member.getId());
        memberDto.setName(member.getName());
        memberDto.setPwd(member.getPwd());
        memberDto.setAuthority(member.getAuthority().getAuthority());

        return memberDto;
    }

}