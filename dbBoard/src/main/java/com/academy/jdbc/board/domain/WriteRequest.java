package com.academy.jdbc.board.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WriteRequest {
    @Size(min = 2, max = 50)
    private String title;

    @Size(min = 2, max = 300)
    private String content;

}
