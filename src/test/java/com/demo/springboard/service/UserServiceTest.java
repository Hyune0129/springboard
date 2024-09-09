package com.demo.springboard.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.mock.mockito.MockBean;
import com.demo.springboard.UserDTO;
import com.demo.springboard.mapper.UserMapper;

public class UserServiceTest {

    @MockBean
    UserMapper userMapper;

    @InjectMocks
    UserService userService;

    @Test
    void testGetUserById() {
        UserDTO user =
                UserDTO.builder().id("testId").password("testPassword").name("testName").build();
        when(userMapper.getUserById("testId")).thenReturn(user);
        assertEquals(user, userService.getUserById("testId"));
        assertEquals(null, userService.getUserById("notExistId"));
    }

    @Test
    void getBlankErrorTest() {
        Map<String, Boolean> errors = userService.getBlankError("", "", "");
        assertTrue(errors.get("isIdBlank"));
        assertTrue(errors.get("isPasswordBlank"));
        assertTrue(errors.get("isNameBlank"));
        errors = userService.getBlankError(" ", " ", " ");
        assertTrue(errors.get("isIdBlank"));
        assertTrue(errors.get("isPasswordBlank"));
        assertTrue(errors.get("isNameBlank"));
        errors = userService.getBlankError("testId", "testPassword", "testName");
        assertFalse(errors.get("isIdBlank"));
        assertFalse(errors.get("isPasswordBlank"));
        assertFalse(errors.get("isNameBlank"));
    }


}
