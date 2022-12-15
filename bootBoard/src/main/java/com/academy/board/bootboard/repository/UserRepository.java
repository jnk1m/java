package com.academy.board.bootboard.repository;

import com.academy.board.bootboard.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    boolean existsByUsername(String username);


    Optional<User> findByUsername(String username);
}
