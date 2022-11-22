package com.academy.edu.jdbc.repository;

import com.academy.edu.jdbc.service.login.User;
import com.academy.edu.jdbc.service.login.UserRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class JdbcUserRepository implements UserRepository {
    private final JdbcTemplate jdbcTemplate;

    public JdbcUserRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query(
                "SELECT id, username, password, created_at FROM JdbcUsers",
                (rs, rowNum) -> new User(
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getTimestamp("created_at")));
    }

    @Override
    public User findById(int id) {
        return jdbcTemplate.queryForObject("SELECT id, username, password, created_at FROM JdbcUsers WHERE id =?",
                (rs, rowNum)->
                new User(rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getTimestamp("created_at")),id);
    }
}
