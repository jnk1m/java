package com.academy.jdbc.board.mapper;

import com.academy.jdbc.board.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserMapper {
    Optional<User> selectUser(String username);

    List<User> selectUsers();

}
