package com.academy.board.bootboard.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "BoardPost")
@Getter
@NoArgsConstructor
@DynamicInsert //테이블에 디폴트가 걸려 있으면 jpa에서 디폴트로 넣어주는..
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer postId;

    private String title;

    private String content;

    @ManyToOne
    @JoinColumn(name = "created_by")
    private User createdBy;

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

    @OneToMany(mappedBy = "post", orphanRemoval = true)
    private List<Like> likeList;

    public Post(String title, String content, User createdBy, boolean visibility) {
        this.title = title;
        this.content = content;
        this.createdBy = createdBy;
        this.visibility = visibility;
    }
}
