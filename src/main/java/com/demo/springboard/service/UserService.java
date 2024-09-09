package com.demo.springboard.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import com.demo.springboard.UserDTO;
import com.demo.springboard.mapper.UserMapper;
import com.demo.springboard.util.UserValidator;

@Service
public class UserService {

    private final UserMapper userMapper;

    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
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

    public Map<String, Boolean> getBlankError(String id, String password, String name) {
        Map<String, Boolean> errors = new HashMap<>();
        if (id == null || id.isBlank()) {
            errors.put("isIdBlank", true);
        }
        if (password == null || password.isBlank()) {
            errors.put("isPasswordBlank", true);
        }
        if (name == null || name.isBlank()) {
            errors.put("isNameBlank", true);
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

    public Map<String, Boolean> getRegisterValidateError(UserDTO user) {
        final Map<String, Boolean> errors = new HashMap<>();
        if (!UserValidator.isValidId(user.getId())) {
            errors.put("invaildId", true);
        }
        if (!UserValidator.isValidPassword(user.getPassword())) {
            errors.put("invaildPassword", true);
        }
        if (!UserValidator.isValidName(user.getName())) {
            errors.put("invaildName", true);
        }
        return errors;
    }

    public void register(UserDTO user) {
        userMapper.insertUser(user);
    }

    public Map<String, Boolean> getDuplicateError(UserDTO user) {
        Map<String, Boolean> errors = new HashMap<>();
        if (userMapper.isExistId(user.getId())) {
            errors.put("isDuplicatedID", true);
        }
        if (userMapper.isExistName(user.getName())) {
            errors.put("isDuplicatedName", true);
        }
        return errors;
    }



}
