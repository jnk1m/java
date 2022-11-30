package com.academy.jdbc.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "BoardPost")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int postId;

    private String title;

    private String content;

//    @Column(name = "created_by")
    @ManyToOne
    @JoinColumn(name = "created_by")
    private User createdBy;

//    @Column(name = "updated_by")
    @ManyToOne
    @JoinColumn(name = "updated_by")
    private User updatedBy;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    private boolean visibility;


    @OneToMany(mappedBy = "postId")
    private List<Comment> commentList;

//    @OneToMany(mappedBy = "postId")
//    private List<Like> likeList;

}
