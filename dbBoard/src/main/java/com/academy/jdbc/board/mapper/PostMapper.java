package com.academy.jdbc.board.mapper;

import com.academy.jdbc.board.DTO.CommentDTO;
import com.academy.jdbc.board.DTO.PostDTO;
import com.academy.jdbc.board.domain.Board;
import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface PostMapper {
    @Transactional(readOnly = true)
    Optional<PostDTO> selectPost(int id);

    List<Board> getPostList();
    List<Board> getDeletedPostList();

    List<CommentDTO> selectComments(int postId);

    @Transactional
    int insertPost(@Param("title") String title, @Param("content") String content, @Param("userId") int userId);

    @Transactional
    int insertComment(@Param("content") String content, @Param("postId") int postId, @Param("writer") int writer);

    @Transactional
    void updatePost(@Param("title") String title,
                    @Param("content") String content,
                    @Param("updateUserId") int updateUserId,
                    @Param("postId") int postId);

    @Transactional
    void setPostInvisible(@Param("postId") int postId);

    @Transactional
    void setPostVisible(@Param("postId") int postId);
}
