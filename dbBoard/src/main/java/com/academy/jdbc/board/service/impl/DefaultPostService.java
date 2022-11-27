package com.academy.jdbc.board.service.impl;

import com.academy.jdbc.board.DTO.CommentDTO;
import com.academy.jdbc.board.DTO.PostDTO;
import com.academy.jdbc.board.domain.Board;
import com.academy.jdbc.board.domain.Comment;
import com.academy.jdbc.board.domain.Post;
import com.academy.jdbc.board.exception.NoPermissionException;
import com.academy.jdbc.board.mapper.PostMapper;
import com.academy.jdbc.board.service.PostService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public List<Board> getAllDeletedPosts() {
        return postMapper.getDeletedPostList();
    }

    @Override
    public List<Optional<Comment>> getAllComments(int postId) {
        List<CommentDTO> commentDTOS = postMapper.selectComments(postId);
        List<Optional<Comment>> commentList = new ArrayList<>();

        for (CommentDTO commentDTO : commentDTOS) {
            commentList.add(Optional.of(new Comment(commentDTO.getId(),
                    commentDTO.getContent(),
                    commentDTO.getPost_id(),
                    commentDTO.getWriter(),
                    commentDTO.getCreated_at(),
                    commentDTO.getVisibility())));
        }

        return commentList;
    }

    @Override
    public void writePost(String title, String content, int userId) {
        postMapper.insertPost(title, content, userId);
    }

    @Override
    public void writeComment(String content, int postId, int userId) {
        postMapper.insertComment(content, postId, userId);
    }

    @Override
    public void updatePost(String title, String content, int updateUserId, int postId) throws NoPermissionException {
        if (!isAuthUser(updateUserId, postId)) {
            throw new NoPermissionException();
        }

        postMapper.updatePost(title, content, updateUserId, postId);
    }

    @Override
    public void setPostInvisible(int updateUserId, int postId) throws NoPermissionException {
        if (!isAuthUser(updateUserId, postId)) {
            throw new NoPermissionException();
        }
        postMapper.setPostInvisible(postId);
    }

    @Override
    public void setPostVisible(int postId) {
        postMapper.setPostVisible(postId);
    }

    private boolean isAuthUser(int updateUserId, int postId) {

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
