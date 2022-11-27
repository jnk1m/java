package com.academy.jdbc.board.domain;

import lombok.Getter;

import java.util.Date;

@Getter
public class Comment {
    private final int id;
    private final String content;
    private final int post_id;
    private final User writer;
    private final Date created_at;
    private final int visibility;

    public Comment(int id, String content, int post_id, User writer, Date created_at, int visibility) {
        this.id = id;
        this.content = content;
        this.post_id = post_id;
        this.writer = writer;
        this.created_at = created_at;
        this.visibility = visibility;
    }


}
