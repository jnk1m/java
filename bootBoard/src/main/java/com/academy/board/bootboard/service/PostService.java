package com.academy.board.bootboard.service;

import com.academy.board.bootboard.entity.Post;
import com.academy.board.bootboard.exception.PostNotFoundException;

import java.util.List;

public interface PostService {
    List<Post> getAllPosts();

    List<Post> getVisiblePosts(boolean isTrue);

    Post getPost(int postId) throws PostNotFoundException;

    void writePost(String title, String content, int userId);
}
