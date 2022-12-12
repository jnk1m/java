package com.academy.board.bootboard.service;

import com.academy.board.bootboard.entity.Post;

public interface CommentService {
    int getCommentCountByPostId(Post postId);
}
