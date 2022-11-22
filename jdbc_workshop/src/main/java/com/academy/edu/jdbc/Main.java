package com.academy.edu.jdbc;

import com.academy.edu.jdbc.repository.JdbcUserRepository;
import com.academy.edu.jdbc.service.login.User;
import com.academy.edu.jdbc.service.login.UserRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {

        loadDriver();
        Connection connection = DbUtils.getDataSource().getConnection();

        UserRepository userRepository = new JdbcUserRepository();
        List<User> all = userRepository.findAll(connection);
        all.forEach(System.out::println);

        System.out.println(userRepository.findById(connection,1L));

    }


    private static void loadDriver() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}