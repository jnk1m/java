package com.academy.jdbc.repository;

import com.academy.jdbc.entity.Comment;
import org.springframework.data.repository.NoRepositoryBean;

import java.time.LocalDateTime;
import java.util.List;

@NoRepositoryBean
public interface CommentRepositoryCustom {
    List<Comment> getWriteDate(LocalDateTime writeDate);
}
