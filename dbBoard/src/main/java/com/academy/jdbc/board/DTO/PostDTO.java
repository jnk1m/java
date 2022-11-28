package com.academy.jdbc.board.DTO;

import com.academy.jdbc.board.domain.User;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class PostDTO {
    private int id;
    private String title;
    private String content;
    private User created_by;
    private User updated_by;
    private Date created_at;
    private Date updated_at;
    private int visibility;
}
