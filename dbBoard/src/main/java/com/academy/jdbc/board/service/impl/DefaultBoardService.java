package com.academy.jdbc.board.service.impl;

import com.academy.jdbc.board.domain.Board;
import com.academy.jdbc.board.mapper.BoardMapper;
import com.academy.jdbc.board.service.BoardService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultBoardService implements BoardService {
    BoardMapper boardMapper;

    public DefaultBoardService(BoardMapper boardMapper) {
        this.boardMapper = boardMapper;
    }

    @Override
    public List<Board> getAllBoard() {
        return boardMapper.getBoardList();
    }


}
