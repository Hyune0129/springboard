package com.demo.springboard.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.HashMap;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import com.demo.springboard.BoardDTO;
import com.demo.springboard.service.BoardService;
import com.demo.springboard.service.UserService;
import jakarta.servlet.http.HttpSession;

@WebMvcTest(BoardController.class)
@DisplayName("BoardController slice test")
public class BoardControllerTest {

    @MockBean
    private UserService userService;

    @MockBean
    private BoardService boardService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getIndex() throws Exception {
        mockMvc.perform(get("/")).andExpect(status().isOk());
    }

    @Test
    void getDescription() throws Exception {
        BoardDTO testBoard = BoardDTO.builder().bid(1L).title("test").time("2024-01-01")
                .writer("testName").description("testDescription").build();
        when(boardService.getBoardById(1L)).thenReturn(testBoard);
        mockMvc.perform(get("/board/1")).andExpect(status().isOk())
                .andExpect(model().attribute("board", testBoard));
    }

    @Test
    void getWritePageTest() throws Exception {
        when(userService.hasSession(any(HttpSession.class))).thenReturn(true);
        mockMvc.perform(get("/board/create")).andExpect(status().isOk());
    }

    @Test
    void createBoard() throws Exception {
        when(userService.hasSession(any(HttpSession.class))).thenReturn(true);
        when(boardService.getBlankError(any(BoardDTO.class)))
                .thenReturn(new HashMap<String, Boolean>());;
        mockMvc.perform(post("/board/create")).andExpect(status().is3xxRedirection());

    }
}
