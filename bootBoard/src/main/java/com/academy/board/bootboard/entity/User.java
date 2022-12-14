package com.academy.board.bootboard.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

@Entity
@Table(name = "BoardUser")
@Getter
@NoArgsConstructor
@DynamicInsert
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer userId;

    @Column(name = "user_name")
    private String username;

    private String password;

    @ManyToOne
    @JoinColumn(name = "role", insertable = false, updatable = false)
    private Role role;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
