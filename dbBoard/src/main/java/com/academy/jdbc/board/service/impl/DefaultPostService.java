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
        return post.map(postDTO -> new Post(postDTO.getId(),
                postDTO.getTitle(),
                postDTO.getContent(),
                postDTO.getCreated_by(),
                postDTO.getUpdated_by(),
                postDTO.getCreated_at(),
                postDTO.getUpdated_at(),
                postDTO.getVisibility() == 1));
    }

    @Override
    public Optional<Comment> getComment(int id) {
        Optional<CommentDTO> comment = postMapper.selectComment(id);
        return comment.map(commentDTO -> new Comment(commentDTO.getId(),
                commentDTO.getContent(),
                commentDTO.getPost_id(),
                commentDTO.getWriter(),
                commentDTO.getCreated_at(),
                commentDTO.getVisibility()));
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
        if (!isAuthUserPost(updateUserId, postId)) {
            throw new NoPermissionException();
        }

        postMapper.updatePost(title, content, updateUserId, postId);
    }

    @Override
    public void setPostInvisible(int updateUserId, int postId) throws NoPermissionException {
        if (!isAuthUserPost(updateUserId, postId)) {
            throw new NoPermissionException();
        }
        postMapper.setPostInvisible(postId);
    }

    @Override
    public void setPostVisible(int postId) {
        postMapper.setPostVisible(postId);
    }

    @Override
    public void updateComment(String content, int commentId, int writerId, int userId) throws NoPermissionException {
        if (writerId != userId) {
            throw new NoPermissionException();
        }

        postMapper.updateComment(content, commentId);

    }

    @Override
    public void setCommentInvisible(int userId, int commentId) throws NoPermissionException {

        if (getComment(commentId).get().getWriter().getId() != userId) {
            throw new NoPermissionException();
        }

        postMapper.setCommentInvisible(commentId);

    }

    @Override
    public int getLikeCount(int postId) {
        return postMapper.selectLikeCount(postId);
    }

    private boolean isAuthUserPost(int updateUserId, int postId) {

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
