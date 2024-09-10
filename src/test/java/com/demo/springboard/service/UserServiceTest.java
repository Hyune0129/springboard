package com.demo.springboard.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.demo.springboard.UserDTO;
import com.demo.springboard.mapper.UserMapper;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
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
        assertFalse(errors.containsKey("isIdBlank"));
        assertFalse(errors.containsKey("isPasswordBlank"));
        assertFalse(errors.containsKey("isNameBlank"));
    }

    @Test
    void getValidateErrorTest() {
        UserDTO user = UserDTO.builder().id("testId1234").password("testPassword1").name("testName")
                .build();
        Map<String, Boolean> errors = userService.getValidateError("testPassword1", user);
        assertFalse(errors.containsKey("isNotMatch"));
        errors = userService.getValidateError("invalidPassword", user);
        assertTrue(errors.get("isNotMatch"));
    }

    @Test
    void getRegisterValidateErrorTest() {
        UserDTO user = UserDTO.builder().id("testId1234").password("testPassword1").name("testName")
                .build();
        Map<String, Boolean> errors = userService.getRegisterValidateError(user);
        assertFalse(errors.containsKey("invaildId"));
        assertFalse(errors.containsKey("invaildPassword"));
        assertFalse(errors.containsKey("invaildName"));
        UserDTO invaildUser = UserDTO.builder().id("testId123456789012345678901")
                .password("invalidPassword1111123132131").name("1111asdf").build();
        errors = userService.getRegisterValidateError(invaildUser);
        assertTrue(errors.get("invaildId"));
        assertTrue(errors.get("invaildPassword"));
        assertTrue(errors.get("invaildName"));
    }

    @Test
    void getDuplicateErrorTest() {
        UserDTO user = UserDTO.builder().id("testId1234").password("testPassword1").name("testName")
                .build();
        UserDTO vaildUser = UserDTO.builder().id("vaildId1234").password("vaildPassword1")
                .name("vaildName").build();
        when(userMapper.isExistId("testId1234")).thenReturn(true);
        when(userMapper.isExistName("testName")).thenReturn(true);
        when(userMapper.isExistId("vaildId1234")).thenReturn(false);
        when(userMapper.isExistName("vaildName")).thenReturn(false);
        Map<String, Boolean> errors = userService.getDuplicateError(user);
        assertTrue(errors.get("isDuplicatedID"));
        assertTrue(errors.get("isDuplicatedName"));
        errors = userService.getDuplicateError(vaildUser);
        assertFalse(errors.containsKey("isDuplicatedID"));
        assertFalse(errors.containsKey("isDuplicatedName"));
    }
}
