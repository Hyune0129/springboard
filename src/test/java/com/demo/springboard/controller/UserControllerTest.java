package com.demo.springboard.controller;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
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
                final long uid = 1;
                final String name = "test";
                Map<String, Boolean> errors = new HashMap<>();

                UserDTO user = UserDTO.builder().id(id).password(password).uid(uid).name(name)
                                .build();

                when(userService.getUserById(id)).thenReturn(user);
                when(userService.getBlankError(id, password)).thenReturn(errors);
                when(userService.getValidateError(password, user)).thenReturn(errors);

                mockMvc.perform(MockMvcRequestBuilders.post("/user/login").param("id", id)
                                .param("password", password)).andExpect(status().is3xxRedirection())
                                .andExpect(redirectedUrl("/"));
                // redirect to login page

        }

        @Test
        @DisplayName("get register page")
        void getRegisterPage() throws Exception {
                mockMvc.perform(get("/user/register")).andExpect(status().isOk());
        }

        @Test
        @DisplayName("register process")
        void register() throws Exception {
                final String id = "testid1234";
                final String password = "testpassword11";
                final String confirm = "testpassword11";
                final String name = "testname1234";

                UserDTO user = UserDTO.builder().id(id).password(password).name(name).build();
                Map<String, Boolean> errors = new HashMap<>();
                when(userService.getBlankError(id, password, name)).thenReturn(errors);
                when(userService.getUserById(id)).thenReturn(user);
                when(userService.getValidateError(password, user)).thenReturn(errors);

                mockMvc.perform(MockMvcRequestBuilders.post("/user/register").param("id", id)
                                .param("password", password).param("confirm", confirm)
                                .param("name", name)).andExpect(status().is2xxSuccessful());
        }

        @Test
        void logout() throws Exception {
                MockHttpSession mockHttpSession = new MockHttpSession();
                mockMvc.perform(get("/user/logout").session(mockHttpSession))
                                .andExpect(status().is3xxRedirection())
                                .andExpect(redirectedUrl("/"));
        }
}
