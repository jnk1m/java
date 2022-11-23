package com.academy.jdbc.board.domain;

import java.util.Date;

public class Reply {
    private int id;
    private final String content;
    private final int postId;
    private Date createAt;
    private boolean visibility;
    private int writer;

    public Reply(String content, int postId, int writer) { //WHEN INSERT
        this.content = content;
        this.postId = postId;
        this.writer = writer;
    }

    public Reply(int id, String content, int postId, Date createAt, boolean visibility, int writer) { //WHEN SELECT
        this.id = id;
        this.content = content;
        this.postId = postId;
        this.createAt = createAt;
        this.visibility = visibility;
        this.writer = writer;
    }

    public int getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public int getPostId() {
        return postId;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public boolean isVisibility() {
        return visibility;
    }

    public int getWriter() {
        return writer;
    }

    @Override
    public String toString() {
        return "Reply{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", postId=" + postId +
                ", createAt=" + createAt +
                ", visibility=" + visibility +
                ", writer=" + writer +
                '}';
    }
}
