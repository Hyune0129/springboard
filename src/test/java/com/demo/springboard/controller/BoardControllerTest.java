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
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import com.demo.springboard.BoardDTO;
import com.demo.springboard.UserDTO;
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

    @Test
    void getChangePostPageTest() throws Exception {
        BoardDTO board = BoardDTO.builder().bid(1L).description("testDescription")
                .title("testTitle").time("2024-01-01 10:10:10").writer("tester1").build();
        UserDTO user =
                UserDTO.builder().id("testid1").uid(1L).name("tester1").password("1234").build();
        MockHttpSession httpSession = new MockHttpSession();
        when(boardService.getBoardById(1L)).thenReturn(board);
        when(boardService.isWriter(board, user)).thenReturn(true);
        httpSession.setAttribute("user", user);
        mockMvc.perform(get("/board/update/1").session(httpSession)).andExpect(status().isOk())
                .andExpect(model().attribute("board", board));
    }

    @Test
    void updatePostTest() throws Exception {
        BoardDTO changedBoard = BoardDTO.builder().bid(1L).description("changedDescription")
                .title("changedTitle").time("2024-01-01 10:10:10").writer("tester1").build();
        UserDTO user =
                UserDTO.builder().id("testid1").uid(1L).name("tester1").password("1234").build();
        MockHttpSession httpSession = new MockHttpSession();
        when(boardService.getBoardById(1L)).thenReturn(changedBoard);
        httpSession.setAttribute("user", user);

        mockMvc.perform(post("/board/update/1").session(httpSession).param("title", "changedTitle")
                .param("description", "changedDescription").param("writer", "tester1"))
                .andExpect(model().attribute("isWriter", true)).andExpect(status().isOk())
                .andExpect(model().attribute("board", changedBoard));

    }
}
