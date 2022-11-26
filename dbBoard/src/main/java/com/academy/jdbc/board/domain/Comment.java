package com.academy.jdbc.board.domain;

import lombok.Getter;

import java.util.Date;

@Getter
public class Comment {
    private int id;
    private String content;
    private int post_id;
    private User writer;
    private Date created_at;
    private int visibility;

    public Comment(int id, String content, int post_id, User writer, Date created_at, int visibility) {
        this.id = id;
        this.content = content;
        this.post_id = post_id;
        this.writer = writer;
        this.created_at = created_at;
        this.visibility = visibility;
    }


}
