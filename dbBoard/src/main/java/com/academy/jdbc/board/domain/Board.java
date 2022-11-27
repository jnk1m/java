package com.academy.jdbc.board.domain;

import lombok.Getter;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
public class Board {
    private final int id;
    private final String title;
    private final String created_by;
    private String updated_by;
    private final Timestamp created_at;
    private final long commentCount;

    public static String doFormatDate(Date date){
        return new SimpleDateFormat("yyyy/MM/dd HH:mm").format(date);
    }

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

    @Override
    public String toString() {
        return "Board{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", created_by='" + created_by + '\'' +
                ", updated_by='" + updated_by + '\'' +
                ", created_at=" + created_at +
                ", commentCount=" + commentCount +
                '}';
    }
}
