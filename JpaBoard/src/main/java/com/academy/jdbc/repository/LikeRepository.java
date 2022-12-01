package com.academy.jdbc.repository;

import com.academy.jdbc.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LikeRepository extends JpaRepository<Like, Like.Pk> {
    List<Like> getAllByLiked(boolean liked);
}
