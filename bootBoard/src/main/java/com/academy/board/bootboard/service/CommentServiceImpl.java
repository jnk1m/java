package com.academy.board.bootboard.service;

import com.academy.board.bootboard.entity.Post;
import com.academy.board.bootboard.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;

    @Override
    public int getCommentCountByPostId(Post postId) {
        return commentRepository.countByPostId(postId);
    }
}
