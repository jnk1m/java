package com.academy.jdbc.board.service;

import com.academy.jdbc.board.domain.Board;
import com.academy.jdbc.board.domain.Comment;
import com.academy.jdbc.board.domain.Post;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public interface PostService {
    Optional<Post> getPost(int postId);

    List<Comment> getAllComments(int postId);

    List<Board> getAllPosts();

    int writePost(String title, String content, int userId);

    void updatePost(String title, String content, int updateUserId, int postId);

    void setPostInvisible(@Param("postId") int postId);



//    List<HashMap<String,Object>> getCommentCount();
}
