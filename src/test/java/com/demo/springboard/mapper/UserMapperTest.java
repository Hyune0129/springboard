package com.demo.springboard.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.transaction.annotation.Transactional;
import com.demo.springboard.UserDTO;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    private UserDTO testUser;

    @BeforeEach
    void setUp() {
        testUser = UserDTO.builder().name("existUser").password("existPassword").id("existId1234")
                .build();
        userMapper.insertUser(testUser);
    }

    @Test
    void insertUser() {
        UserDTO user = UserDTO.builder().name("testuser").password("testpassword").id("testid1234")
                .build();
        userMapper.insertUser(user);
    }

    @Test
    void getUserById() {
        UserDTO user = userMapper.getUserById("existId1234");
        assertEquals("existUser", user.getName());
    }

    @Test
    void isExistName() {
        boolean result = userMapper.isExistName("existUser");
        assertTrue(result);
        assertFalse(userMapper.isExistName("neverRegisteredName"));
    }

    @Test
    void isExistId() {
        assertTrue(userMapper.isExistId("existId1234"));
        assertFalse(userMapper.isExistId("nonExistentId"));
    }
}
