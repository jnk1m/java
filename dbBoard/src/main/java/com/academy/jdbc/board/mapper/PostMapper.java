package com.academy.jdbc.board.mapper;

import com.academy.jdbc.board.domain.Board;
import com.academy.jdbc.board.domain.Comment;
import com.academy.jdbc.board.domain.Post;
import com.academy.jdbc.board.DTO.PostDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public interface PostMapper {
    @Transactional(readOnly = true)
    Optional<PostDTO> selectPost(int id);

    List<Board> getPostList();

//    List<Post> selectPosts();

    List<Comment> selectComments(int postId);

    @Transactional
    int insertPost(@Param("title") String title, @Param("content") String content, @Param("userId") int userId);

    @Transactional
    void updatePost(@Param("title") String title,
                    @Param("content") String content,
                    @Param("updateUserId") int updateUserId,
                    @Param("postId") int postId);

    void setPostInvisible(@Param("postId") int postId);
}
