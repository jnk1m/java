package com.academy.jdbc.board.domain;

public class User {
    private int id;
    private final String userName;
    private final String password;
    private final int role;

    public User(int id, String userName, String password, int role) { //WHEN SELECT
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.role = role;
    }

    public User(String userName, String password, int role) { // WHEN INSERT..
        this.userName = userName;
        this.password = password;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public int getRole() {
        return role;
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
