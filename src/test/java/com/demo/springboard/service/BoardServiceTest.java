package com.demo.springboard.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import java.util.Arrays;
import java.util.Map;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.demo.springboard.BoardDTO;
import com.demo.springboard.UserDTO;
import com.demo.springboard.mapper.BoardMapper;


@ExtendWith(MockitoExtension.class)
public class BoardServiceTest {

    @InjectMocks
    private BoardService boardService;

    @Mock
    private BoardMapper boardMapper;

    @Test
    void getBoardsTest() {
        BoardDTO testBoard1 = BoardDTO.builder().bid(1L).title("test").time("2024-01-01").build();
        BoardDTO testBoard2 = BoardDTO.builder().bid(2L).title("test").time("2024-01-01").build();
        when(boardMapper.getBoards()).thenReturn(Arrays.asList(testBoard1, testBoard2));
        List<BoardDTO> boards = boardService.getBoards();
        assertEquals(testBoard1, boards.get(0));
        assertEquals(testBoard2, boards.get(1));
    }

    @Test
    void getBoardByIdTest() {
        BoardDTO testBoard = BoardDTO.builder().bid(1L).title("test").time("2024-01-01").build();
        when(boardMapper.getBoardById(1L)).thenReturn(testBoard);

        BoardDTO boardDTO = boardService.getBoardById(1L);
        assertEquals(boardDTO.getBid(), testBoard.getBid());
        assertEquals(testBoard, boardDTO);
    }

    @Test
    void getBlankErrorTest() throws Exception {
        Map<String, Boolean> errors;
        BoardDTO board;
        // title blank
        board = BoardDTO.builder().title("").description("test").build();
        errors = boardService.getBlankError(board);
        assertTrue(errors.getOrDefault("isTitleBlank", false));

        // description blank
        board = BoardDTO.builder().title("abc").description("").build();
        errors = boardService.getBlankError(board);
        assertTrue(errors.getOrDefault("isDescriptionBlank", false));

        // not error
        board = BoardDTO.builder().title("abc").description("abc").build();
        errors = boardService.getBlankError(board);
        assertTrue(errors.isEmpty());
    }

    @Test
    void isWriterTest() throws Exception {
        BoardDTO board = BoardDTO.builder().writer("tester").build();
        BoardDTO nullBoard = null;
        UserDTO user = UserDTO.builder().name("tester").build();
        UserDTO nullUser = null;
        assertFalse(boardService.isWriter(nullBoard, user));
        assertFalse(boardService.isWriter(board, nullUser));
        assertTrue(boardService.isWriter(board, user));
    }
}
