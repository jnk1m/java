package com.academy.jdbc.board.domain;

import lombok.Getter;

import java.sql.Timestamp;
import java.util.Date;

@Getter
public class Post {

    private int id;
    private final String title;
    private String content;
    private final int writer;
    private int modifier;
    private final Timestamp createdAt;
    private Timestamp modifiedAt;
    private boolean visibility;
    private long commentCount;


    public Post(int id, String title, int writer, Timestamp createdAt, long commentCount) {
        this.id = id;
        this.title = title;
        this.writer = writer;
        this.createdAt = createdAt;
        this.commentCount = commentCount;
    }



}
