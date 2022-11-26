package com.academy.jdbc.board.domain;

import lombok.Getter;

import java.sql.Timestamp;

@Getter
public class Board {
    private final int id;
    private final String title;
    private final String created_by;
    private String updated_by;
    private final Timestamp created_at;
    private final long commentCount;

    public Board(int id, String title, String created_by, Timestamp created_at, long commentCount) {
        this.id = id;
        this.title = title;
        this.created_by = created_by;
        this.created_at = created_at;
        this.commentCount = commentCount;
    }

    public Board(int id, String title, String created_by, String updated_by, Timestamp created_at, long commentCount) {
        this.id = id;
        this.title = title;
        this.created_by = created_by;
        this.updated_by = updated_by;
        this.created_at = created_at;
        this.commentCount = commentCount;
    }


}
