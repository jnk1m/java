package com.academy.score.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Getter
@NoArgsConstructor
public class StudentRegisterRequest {
    @NotBlank
    String name;

    @Email
    String email;

    @Min(0)
    @Max(100)
    int score;

    @NotBlank
    @Size(min = 0, max = 200)
    String comment;
}

//리플렉션 순서.. 생성자가 기본으로 있어야 함. 없으면 getter -> setter를 찾음. 관련된 걸 찾아서 불러버림.



