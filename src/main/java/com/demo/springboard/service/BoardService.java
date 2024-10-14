package com.demo.springboard.service;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import org.springframework.stereotype.Service;

import com.demo.springboard.BoardDTO;
import com.demo.springboard.UserDTO;
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

    public Map<String, Boolean> getBlankError(BoardDTO board) {
        Map<String, Boolean> errors = new HashMap<>();
        if (board.getTitle().isBlank()) {
            errors.put("isTitleBlank", true);
        }
        if (board.getDescription().isBlank()) {
            errors.put("isDescriptionBlank", true);
        }
        return errors;
    }

    public void insertBoard(BoardDTO board, UserDTO user) {
        Map<String, Object> boardInfo = new HashMap<>();
        boardInfo.put("writer", user.getUid());
        boardInfo.put("title", board.getTitle());
        boardInfo.put("description", board.getDescription());
        boardMapper.insertBoard(boardInfo);
    }

    public void updateBoard(BoardDTO board) {
        boardMapper.updateBoard(board);
    }

    public boolean isWriter(BoardDTO board, UserDTO user) {
        if (board == null || user == null) {
            return false;
        }
        return board.getWriter().equals(user.getName());
    }

    public void deleteboardByBid(Long bid) {
        boardMapper.deleteBoardById(bid);
    }

}
