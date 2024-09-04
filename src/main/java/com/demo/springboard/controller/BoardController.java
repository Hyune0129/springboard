package com.demo.springboard.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.springboard.BoardDTO;
import com.demo.springboard.service.BoardService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
public class BoardController {

    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("/")
    public String index(Model model, HttpServletRequest httpServletRequest) {
        List<BoardDTO> boards = boardService.getBoards();
        model.addAttribute("boards", boards);
        HttpSession session = httpServletRequest.getSession();
        if (session.getAttribute("user") != null) {
            model.addAttribute(session.getAttribute("user"));
        }
        return "index";
    }
}
