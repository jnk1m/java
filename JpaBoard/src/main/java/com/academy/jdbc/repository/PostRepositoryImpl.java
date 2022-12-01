package com.academy.jdbc.repository;

import com.academy.jdbc.dto.BoardDto;
import com.academy.jdbc.dto.QBoardDto;
import com.academy.jdbc.entity.Post;
import com.academy.jdbc.entity.QComment;
import com.academy.jdbc.entity.QPost;
import com.academy.jdbc.entity.QUser;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class PostRepositoryImpl extends QuerydslRepositorySupport implements PostRepositoryCustom{
    public PostRepositoryImpl() {
        super(Post.class);
    }


    @Override
    public List<BoardDto> getBoardList() {
        QPost post = QPost.post;
        QUser user = QUser.user;
        QComment comment = QComment.comment;

        return from(post)
                .select(new QBoardDto(
                        post.postId,
                        post.title,
                        user.username,
                        user.username,
                        post.createdAt
                ))
                .innerJoin(user.userId, post)
                .leftJoin(user.userId, post.updatedBy)
                .leftJoin(comment.postId,post);
    }


}
