package com.demo.springboard.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.demo.springboard.UserDTO;
import com.demo.springboard.mapper.UserMapper;

@Service
public class UserService {

    private final UserMapper userMapper;

    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public void insertUser(UserDTO user) {

    }

    public UserDTO getUserById(String id) {
        return userMapper.getUserById(id);
    }

    public Map<String, Boolean> getBlankError(String id, String password) {
        Map<String, Boolean> errors = new HashMap<>();
        if (id == null || id.isBlank()) {
            errors.put("isIdBlank", true);
        }
        if (password == null || password.isBlank()) {
            errors.put("isPasswordBlank", true);
        }
        return errors;
    }

    public Map<String, Boolean> getValidateError(String password, UserDTO user) {
        Map<String, Boolean> errors = new HashMap<>();
        if (user == null || !user.getPassword().equals(password)) {
            errors.put("isNotMatch", true);
        }
        return errors;
    }
}
