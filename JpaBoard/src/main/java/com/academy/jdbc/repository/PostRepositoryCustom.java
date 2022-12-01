package com.academy.jdbc.repository;

import com.academy.jdbc.dto.BoardDto;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface PostRepositoryCustom {
    List<BoardDto> getBoardList();
}
