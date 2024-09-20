package com.demo.springboard.mapper;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.transaction.annotation.Transactional;
import com.demo.springboard.BoardDTO;
import com.demo.springboard.UserDTO;

@Transactional
@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BoardMapperTest {

    @Autowired
    BoardMapper boardMapper;

    @Autowired
    UserMapper userMapper;

    UserDTO testUser;

    @BeforeEach
    void setUp() {
        testUser =
                UserDTO.builder().name("boardTestName").id("boardTestId").password("1234").build();
        userMapper.insertUser(testUser);
        testUser = userMapper.getUserById(testUser.getId());
    }

    @Test
    @Order(2)
    void getBoardsTest() throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("writer", testUser.getUid());
        map.put("title", "testTitle1");
        map.put("description", "boardTestDescription1");
        boardMapper.insertBoard(map);
        map.clear();
        map.put("writer", testUser.getUid());
        map.put("title", "testTitle2");
        map.put("description", "boardTestDescription2");

        List<BoardDTO> boards = boardMapper.getBoards();
        for (BoardDTO board : boards) {
            if (board.getWriter().equals(testUser.getName())) { // dont get description
                assertTrue(board.getTitle().equals("testTitle1")
                        || board.getTitle().equals("testTitle2"));
            }
        }

    }

    @Test
    @Order(3)
    void getBoardByIdTest() throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("writer", testUser.getUid());
        map.put("title", "testTitle1");
        map.put("description", "boardTestDescription1");
        boardMapper.insertBoard(map);
        List<BoardDTO> boards = boardMapper.getBoards();
        BoardDTO testBoard = null;
        for (BoardDTO board : boards) {
            if (board.getWriter().equals(testUser.getName())) { // dont get description
                testBoard = boardMapper.getBoardById(board.getBid());
                break;
            }
        }
        assertNotNull(testBoard);
        assertTrue(testBoard.getWriter().equals(testUser.getName()));
        assertTrue(testBoard.getTitle().equals("testTitle1"));
        assertTrue(testBoard.getDescription().equals("boardTestDescription1"));
    }

    @Test
    @Order(1)
    void insertBoardTest() throws Exception {
        List<BoardDTO> boards = boardMapper.getBoards();
        int prevSize = boards.size();
        Map<String, Object> map = new HashMap<>();
        map.put("title", "testTitle");
        map.put("writer", testUser.getUid()); // uid is Long type
        map.put("description", "testDescription");
        boardMapper.insertBoard(map);

        boards = boardMapper.getBoards();
        assertTrue(prevSize + 1 == boards.size());
    }
}
