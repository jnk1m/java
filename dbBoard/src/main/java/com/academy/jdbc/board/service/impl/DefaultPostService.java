package com.academy.jdbc.board.service.impl;

import com.academy.jdbc.board.domain.Post;
import com.academy.jdbc.board.mapper.PostMapper;
import com.academy.jdbc.board.service.PostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DefaultPostService implements PostService {
    private final PostMapper postMapper;

    public DefaultPostService(PostMapper postMapper) {
        this.postMapper = postMapper;
    }


    @Override
    public Optional<Post> Post(int postId) {
        return Optional.empty();
    }
}
