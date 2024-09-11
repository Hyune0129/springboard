/*
 * @todo: insert dto test for beforeeach
 */
package com.demo.springboard.mapper;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.transaction.annotation.Transactional;
import com.demo.springboard.BoardDTO;

@Transactional
@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BoardMapperTest {

    @Autowired
    BoardMapper boardMapper;

    @Test
    void getBoardsTest() {
        // BoardDTO testBoard1 = BoardDTO.builder().bid(1).title("testTitle1").time("2024-01-01")
        // .writer("testWriter1").description("testDescription1").build();
        // BoardDTO testBoard2 = BoardDTO.builder().bid(2).title("testTitle2").time("2024-01-01")
        // .writer("testWriter2").description("testDescription2").build();

        List<BoardDTO> boards = boardMapper.getBoards();
        for (BoardDTO board : boards) {
            System.out.println(board);
        }

    }
}
