package com.academy.jdbc.board.service.impl;

import com.academy.jdbc.board.DTO.PostDTO;
import com.academy.jdbc.board.domain.Board;
import com.academy.jdbc.board.domain.Comment;
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
    public Optional<Post> getPost(int postId) {
        Optional<PostDTO> post = postMapper.selectPost(postId);
        if (post.isPresent()) {
            return Optional.of(new Post(post.get().getId(),
                    post.get().getTitle(),
                    post.get().getContent(),
                    post.get().getCreated_by(),
                    post.get().getUpdated_by(),
                    post.get().getCreated_at(),
                    post.get().getUpdated_at(),
                    post.get().getVisibility() == 1));
        }
        return Optional.empty();

    }

    @Override
    public List<Board> getAllPosts() {
        return postMapper.getPostList();
    }

    @Override
    public List<Comment> getAllComments(int postId) {
        return postMapper.selectComments(postId);
    }

    @Override
    public int writePost(String title, String content, int userId) {
        return postMapper.insertPost(title, content, userId);
    }

    @Override
    public void updatePost(String title, String content, int updateUserId, int postId) {
        if (!checkUserAuth(updateUserId, postId)) {
            throw new RuntimeException("권한이 없습니다.");
        }

        postMapper.updatePost(title, content, updateUserId, postId);
    }

    @Override
    public void setPostInvisible(int postId) {
        postMapper.setPostInvisible(postId);
    }

    private boolean checkUserAuth(int updateUserId, int postId) {

        if (updateUserId == 1) {
            return true;
        }

        Optional<Post> post = getPost(postId);
        if (post.get().getCreated_by().getId() == updateUserId) {
            return true;
        }

        return false;


    }
}
