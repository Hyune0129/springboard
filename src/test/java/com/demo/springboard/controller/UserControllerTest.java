package com.demo.springboard.controller;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.demo.springboard.UserDTO;
import com.demo.springboard.service.UserService;

@WebMvcTest(controllers = UserController.class)
@DisplayName("Controller Slice test")
class UserControllerTest {
        @Autowired
        MockMvc mockMvc;

        @MockBean
        UserService userService;

        @Test
        @DisplayName("get login page")
        void getLoginPage() throws Exception {
                mockMvc.perform(get("/user/login")).andExpect(status().isOk());
        }

        @Test
        @DisplayName("login process")
        void login() throws Exception {
                final String id = "testid";
                final String password = "testpassword";
                final int uid = 1;
                final String name = "test";
                Map<String, Boolean> errors = new HashMap<>();

                UserDTO user = UserDTO.builder().id(id).password(password).uid(uid).name(name)
                                .build();

                when(userService.getUserById(id)).thenReturn(user);
                when(userService.getBlankError(id, password)).thenReturn(errors);
                when(userService.getValidateError(password, user)).thenReturn(errors);

                mockMvc.perform(MockMvcRequestBuilders.post("/user/login").param("id", id)
                                .param("password", password)).andExpect(status().is2xxSuccessful());

        }

}
