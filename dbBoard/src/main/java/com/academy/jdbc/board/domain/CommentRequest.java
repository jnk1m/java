package com.academy.jdbc.board.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.Size;

@AllArgsConstructor
@Getter
public class CommentRequest {
    @Size(max = 150)
    String content;
}
