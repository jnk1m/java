package com.academy.nhnmartcs.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Getter
public class Inquiry {
    @Size(min = 2 , max = 200)
    String title;

    InquiryCategory category;

    @Size(min = 0 , max = 4000)
    String content;

    String writeDate;

    String id;

//    List<MultipartFile> file;

    boolean isAnswered;




}
