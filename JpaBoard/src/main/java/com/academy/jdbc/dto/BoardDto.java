package com.academy.jdbc.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Value;

import java.time.LocalDateTime;

@Value
public class BoardDto {

    int postId;
    String title;
    String createdBy;
    String updatedBy;
    LocalDateTime createdAt;

    @QueryProjection
    public BoardDto(int postId, String title, String createdBy, String updatedBy, LocalDateTime createdAt) {
        this.postId = postId;
        this.title = title;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
        this.createdAt = createdAt;
    }
}
