package com.academy.jdbc.entity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "BoardLike")
public class Like {
    @Id
    @EmbeddedId
    private Pk postId;

    @Id
    @EmbeddedId
    private Pk userId;

    private boolean liked;


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
