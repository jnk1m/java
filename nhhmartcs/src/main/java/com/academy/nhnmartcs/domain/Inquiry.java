package com.academy.nhnmartcs.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class Inquiry {
    @Size(min = 2 , max = 200)
    String title;

    InquiryCategory category;

    @Size(min = 0 , max = 4000)
    String content;

    LocalDateTime writeDate;

    String id;
    //첨부파일..

    boolean isAnswered;




}
