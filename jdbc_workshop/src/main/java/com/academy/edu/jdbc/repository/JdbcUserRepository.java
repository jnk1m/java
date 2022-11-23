package com.academy.edu.jdbc.repository;

import com.academy.edu.jdbc.exception.LoginFailException;
import com.academy.edu.jdbc.service.login.User;
import com.academy.edu.jdbc.service.login.UserRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getTimestamp("created_at")));
    }

    @Override
    @Transactional(readOnly = true)
    public User findByUserName(String username) throws LoginFailException {
        try {
            return jdbcTemplate.queryForObject("SELECT id, username, password, created_at FROM JdbcUsers WHERE username =?",
                    (rs, rowNum) ->
                            new User(rs.getInt("id"),
                                    rs.getString("username"),
                                    rs.getString("password"),
                                    rs.getTimestamp("created_at")), username);
        } catch (EmptyResultDataAccessException e) {
            throw new LoginFailException();
        }
    }
}
