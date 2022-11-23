package com.academy.jdbc.board.domain;

import java.util.Date;

public class Post {

    private int id;
    private final String title;
    private final String content;
    private final int writer;
    private int modifier;
    private final Date createdAt;
    private boolean visibility;

    public Post(String title, String content, int writer, Date createdAt) { //Insert 할때 사용
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.createdAt = createdAt;
    }

    //SELECT 해서 가져올 때 사용
    public Post(int id, String title, String content, int writer, int modifier, Date createdAt, Date modifiedAt, boolean visibility) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.modifier = modifier;
        this.createdAt = createdAt;
        this.visibility = visibility;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public int getWriter() {
        return writer;
    }

    public int getModifier() {
        return modifier;
    }

    public Date getCreatedAt() {
        return createdAt;
    }


    public boolean isVisibility() {
        return visibility;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", writer=" + writer +
                ", modifier=" + modifier +
                ", createdAt=" + createdAt +
                ", visibility=" + visibility +
                '}';
    }
}
