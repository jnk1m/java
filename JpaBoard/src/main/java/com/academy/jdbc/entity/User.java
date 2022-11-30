package com.academy.jdbc.entity;

import lombok.Getter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "BoardUser")
@Getter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int userId;

    @Column(name = "user_name")
    private String username;

    private String password;

    @ManyToOne
    @JoinColumn(name = "id")
    private Role role;


}
