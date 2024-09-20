package com.demo.springboard.controller;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.springboard.BoardDTO;
import com.demo.springboard.UserDTO;
import com.demo.springboard.service.BoardService;
import com.demo.springboard.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
public class BoardController {

    private final BoardService boardService;

    private final UserService userService;

    public BoardController(BoardService boardService, UserService userService) {
        this.boardService = boardService;
        this.userService = userService;
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

    @GetMapping("/board/{id}")
    public String getDescription(@PathVariable("id") Long id, Model model) {
        BoardDTO board = boardService.getBoardById(id);
        model.addAttribute("board", board);
        return "description";
    }

    @GetMapping("/board/create")
    public String getWritePage(HttpServletRequest httpServletRequest, Model model) {

        if (!userService.hasSession(httpServletRequest.getSession())) {
            final String error = "you must login to write board";
            model.addAttribute("errorMessage", error);
            return "error";
        }

        return "write";
    }

    @PostMapping("/board/create")
    public String postBoard(String title, String description, HttpServletRequest httpServletRequest,
            Model model) {
        if (!userService.hasSession(httpServletRequest.getSession())) {
            final String error = "you must login to write board";
            model.addAttribute("errorMessage", error);
            return "error";
        }
        UserDTO user = (UserDTO) httpServletRequest.getSession().getAttribute("user");
        BoardDTO board = BoardDTO.builder().title(title).description(description).build();
        Map<String, Boolean> blankErrors = boardService.getBlankError(board);
        if (!blankErrors.isEmpty()) {
            model.addAttribute("errors", blankErrors);
            model.addAttribute("title", title);
            model.addAttribute("description", description);
            return "write";
        }
        boardService.insertBoard(board, user);

        return "redirect:/";
    }

}
