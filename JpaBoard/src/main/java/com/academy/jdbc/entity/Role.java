package com.academy.jdbc.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "BoardUserRole")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int roleId;

    private String role;

    @OneToMany(mappedBy = "userId", targetEntity = User.class)
    private List<User> user;
}
