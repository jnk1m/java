package com.academy.jdbc.board.DTO;

import com.academy.jdbc.board.domain.User;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class CommentDTO {
    private int id;
    private String content;
    private int post_id;
    private User writer;
    private Date created_at;
    private int visibility;
}
