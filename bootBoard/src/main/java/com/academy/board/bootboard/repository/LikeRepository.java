package com.academy.board.bootboard.repository;

import com.academy.board.bootboard.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LikeRepository extends JpaRepository<Like, Like.Pk> {
    List<Like> getAllByLiked(boolean liked);
}
