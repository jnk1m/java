package com.academy.jdbc.board.service.impl;

import com.academy.jdbc.board.domain.Post;
import com.academy.jdbc.board.mapper.PostMapper;
import com.academy.jdbc.board.service.PostService;

import java.util.List;

public class DefaultPostService implements PostService {
    private final PostMapper postMapper;

    public DefaultPostService(PostMapper postMapper) {
        this.postMapper = postMapper;
    }

    @Override
    public List<Post> getPosts() {
        return postMapper.selectPosts();
    }
}
