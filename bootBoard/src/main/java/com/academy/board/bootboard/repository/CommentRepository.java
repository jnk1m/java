package com.academy.board.bootboard.repository;

import com.academy.board.bootboard.entity.Comment;
import com.academy.board.bootboard.entity.Post;
import com.academy.board.bootboard.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
    int countByCreatedBy(User createdBy);

    int countByPostId(Post postId);

    @Modifying
    @Query("update Comment c set c.content = ?1 where c.commentId = ?2")
    int updateComment(String content, int commentId);

}
