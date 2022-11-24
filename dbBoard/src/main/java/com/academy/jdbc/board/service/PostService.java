package com.academy.jdbc.board.service;

import com.academy.jdbc.board.domain.Post;
import com.academy.jdbc.board.domain.User;

import java.util.Optional;

public interface PostService {
    Optional<Post> Post(int postId);
}
