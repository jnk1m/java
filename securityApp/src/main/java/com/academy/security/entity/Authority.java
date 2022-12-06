package com.academy.security.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "Authoroties")
public class Authority {
    @Id
    @Column(name = "member_id")
    private String memberId;

    private String authority;

    @MapsId
    @OneToOne
    @JoinColumn(name = "member_id")
    private Member member;
}
