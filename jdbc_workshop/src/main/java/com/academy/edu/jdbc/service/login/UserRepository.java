package com.academy.edu.jdbc.service.login;

import java.util.List;

public interface UserRepository {
    List<User> findAll();
    User findById(int id);
}
