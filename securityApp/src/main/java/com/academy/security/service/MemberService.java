package com.academy.security.service;

import com.academy.security.domain.MemberCreateRequest;
import com.academy.security.domain.MemberDto;
import com.academy.security.domain.MemberId;

public interface MemberService {

    MemberId createMember(MemberCreateRequest request);

    MemberDto getMember(String id);

}
