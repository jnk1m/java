package com.academy.security.domain;

import lombok.Data;

@Data
public class MemberDto {
    private String id;

    private String name;

    private String pwd;

    private String authority;
}
