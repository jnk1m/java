package com.academy.jdbc.board.domain;

import lombok.Getter;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
public class Post {
    private final int id;
    private final String title;
    private final String content;
    private final User created_by;
    private User updated_by;
    private final Date created_at;
    private Date updated_at;
    private final boolean visibility;

    public Post(int id, String title, String content, User created_by, Date created_at, boolean visibility) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.created_by = created_by;
        this.created_at = created_at;
        this.visibility = visibility;
    }

    public Post(int id, String title, String content, User created_by, User updated_by, Date created_at, Date updated_at, boolean visibility) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.created_by = created_by;
        this.updated_by = updated_by;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.visibility = visibility;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", created_by=" + created_by +
                ", updated_by=" + updated_by +
                ", created_at=" + created_at +
                ", updated_at=" + updated_at +
                ", visibility=" + visibility +
                '}';
    }


}
