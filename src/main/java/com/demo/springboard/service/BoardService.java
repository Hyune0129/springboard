package com.demo.springboard.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.demo.springboard.BoardDTO;
import com.demo.springboard.mapper.BoardMapper;

@Service
public class BoardService {

    private final BoardMapper boardMapper;

    BoardService(BoardMapper boardMapper) {
        this.boardMapper = boardMapper;
    }

    public List<BoardDTO> getBoards() {
        return boardMapper.getBoards();
    }

    public BoardDTO getBoardById(Long bid) {
        return boardMapper.getBoardById(bid);
    }
}
