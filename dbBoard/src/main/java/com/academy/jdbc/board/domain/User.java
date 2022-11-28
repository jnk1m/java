package com.academy.jdbc.board.domain;

import lombok.Getter;

@Getter
public class User {
    private int id;
    private final String userName;
    private final String password;
    private final int role;

    public User(int id, String userName, String password, int role) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.role = role;
    }

    public User(String userName, String password, int role) {
        this.userName = userName;
        this.password = password;
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                '}';
    }
}
