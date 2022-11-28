package com.academy.jdbc.board.mapper;

import com.academy.jdbc.board.domain.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface UserMapper {
    @Transactional(readOnly = true)
    Optional<User> selectUser(String username);

    List<User> selectUsers();

}
