package com.academy.jdbc.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "BoardComment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int commentId;

    private String content;

//    @Column(name = "post_id")
    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post postId;

//    @Column(name = "writer")
    @ManyToOne
    @JoinColumn(name = "writer")
    private User createdBy;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    private boolean visibility;
}
