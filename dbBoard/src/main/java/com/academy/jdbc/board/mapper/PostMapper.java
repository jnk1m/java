package com.academy.jdbc.board.mapper;

import com.academy.jdbc.board.domain.Post;

import java.util.List;
import java.util.Optional;

public interface PostMapper {
    Optional<Post> selectPost(long id);

    List<Post> selectPosts();

    void insertPost(Post post);

    void updateNameById(String name, long id);

    void deleteById(long id);
}
