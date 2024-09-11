package com.demo.springboard.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import com.demo.springboard.BoardDTO;
import com.demo.springboard.service.BoardService;

@WebMvcTest(BoardController.class)
public class BoardControllerTest {

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
        BoardDTO testBoard = BoardDTO.builder().bid(1).title("test").time("2024-01-01")
                .writer("testName").description("testDescription").build();
        when(boardService.getBoardById(1L)).thenReturn(testBoard);
        mockMvc.perform(get("/board/1")).andExpect(status().isOk())
                .andExpect(model().attribute("board", testBoard));
    }

}
