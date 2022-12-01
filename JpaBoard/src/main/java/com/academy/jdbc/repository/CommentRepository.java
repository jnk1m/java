package com.academy.jdbc.repository;

import com.academy.jdbc.entity.Comment;
import com.academy.jdbc.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface CommentRepository extends JpaRepository<Comment,Integer>, CommentRepositoryCustom {
    int countByCreatedBy(User createdBy);

    @Modifying
    @Query("update Comment c set c.content = ?1 where c.commentId = ?2")
    int updateComment(String content, int commentId);

}
