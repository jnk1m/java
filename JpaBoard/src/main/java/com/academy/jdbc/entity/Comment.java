package com.academy.jdbc.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "BoardComment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String content;

    @Column(name = "post_id")
    private int postId;

    @Column(name = "writer")
    private int createdBy;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    private boolean visibility;
}
