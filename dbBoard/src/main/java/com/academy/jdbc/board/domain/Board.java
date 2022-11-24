package com.academy.jdbc.board.domain;

import lombok.Getter;
import lombok.Value;

import java.sql.Timestamp;

@Getter
public class Board {
    private final int id;
    private final String title;
    private String writer;
    private String modifier;
    private final Timestamp createdAt;
    private final long commentCount;

    public Board(int id, String title, String writer, Timestamp createdAt, long commentCount) {
        this.id = id;
        this.title = title;
        this.writer = writer;
        this.createdAt = createdAt;
        this.commentCount = commentCount;
    }

    public Board(int id, String title, String writer, String modifier, Timestamp createdAt, long commentCount) {
        this.id = id;
        this.title = title;
        this.writer = writer;
        this.modifier = modifier;
        this.createdAt = createdAt;
        this.commentCount = commentCount;
    }


}
