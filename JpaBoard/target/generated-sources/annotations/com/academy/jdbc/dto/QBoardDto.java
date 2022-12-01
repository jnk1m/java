package com.academy.jdbc.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.academy.jdbc.dto.QBoardDto is a Querydsl Projection type for BoardDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QBoardDto extends ConstructorExpression<BoardDto> {

    private static final long serialVersionUID = -5419060L;

    public QBoardDto(com.querydsl.core.types.Expression<Integer> postId, com.querydsl.core.types.Expression<String> title, com.querydsl.core.types.Expression<String> createdBy, com.querydsl.core.types.Expression<String> updatedBy, com.querydsl.core.types.Expression<java.time.LocalDateTime> createdAt) {
        super(BoardDto.class, new Class<?>[]{int.class, String.class, String.class, String.class, java.time.LocalDateTime.class}, postId, title, createdBy, updatedBy, createdAt);
    }

}

