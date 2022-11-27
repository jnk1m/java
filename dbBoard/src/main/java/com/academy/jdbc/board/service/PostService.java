package com.academy.jdbc.board.service;

import com.academy.jdbc.board.domain.Board;
import com.academy.jdbc.board.domain.Comment;
import com.academy.jdbc.board.domain.Post;
import com.academy.jdbc.board.exception.NoPermissionException;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public interface PostService {
    Optional<Post> getPost(int postId);

    List<Optional<Comment>> getAllComments(int postId);

    List<Board> getAllPosts();
    List<Board> getAllDeletedPosts();

    void writePost(String title, String content, int userId);
    void writeComment(String content, int postId, int userId);

    void updatePost(String title, String content, int updateUserId, int postId) throws NoPermissionException;

    void setPostInvisible(int updateUserId, int postId) throws NoPermissionException;
    void setPostVisible(int postId);



}
