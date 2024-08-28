package com.demo.springboard.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.springboard.BoardDTO;
import com.demo.springboard.service.BoardService;

@Controller
@RequestMapping("/")
public class BoardController {

    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("/")
    public String index(Model model) {
        List<BoardDTO> boards = boardService.getBoards();
        model.addAttribute("boards", boards);
        return "index";
    }

    @GetMapping("/board/{id}")
    public String getDescription(@PathVariable("id") Long id, Model model) {
        BoardDTO board = boardService.getBoardById(id);
        model.addAttribute("board", board);
        return "description";
    }

}
