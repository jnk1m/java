package com.academy.jdbc.entity;

import javax.persistence.*;

@Entity
@Table(name = "BoardUser")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "user_name")
    private String userName;

    private String password;

    private int role;
}
