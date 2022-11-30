package com.academy.jdbc.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "BoardLike")
@Getter
public class Like {
    @EmbeddedId
    private Pk pk;

    private boolean liked;

    @MapsId("postId")
    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @MapsId("userId")
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode
    @Embeddable
    public static class Pk implements Serializable {
        @Column(name = "post_id")
        private int postId;

        @Column(name = "user_id")
        private int userId;
    }
}

