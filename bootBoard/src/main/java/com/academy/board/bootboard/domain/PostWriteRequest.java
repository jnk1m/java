package com.academy.board.bootboard.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
@Setter
public class PostWriteRequest {
    @Size(min = 2, max = 50)
    private String title;

    @Size(min = 2, max = 300)
    private String content;

}
