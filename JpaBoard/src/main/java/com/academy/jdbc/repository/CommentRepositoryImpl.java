package com.academy.jdbc.repository;

import com.academy.jdbc.entity.Comment;
import com.academy.jdbc.entity.QComment;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.time.LocalDateTime;
import java.util.List;

public class CommentRepositoryImpl extends QuerydslRepositorySupport implements CommentRepositoryCustom {
    public CommentRepositoryImpl() {
        super(Comment.class);
    }

    @Override
    public List<Comment> getWriteDate(LocalDateTime writeDate) {

        QComment comment = QComment.comment;

        return from(comment)
                .where(comment.createdAt.after(writeDate))
                .select(comment)
                .fetch();
    }


}
