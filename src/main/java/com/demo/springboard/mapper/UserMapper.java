package com.demo.springboard.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.demo.springboard.UserDTO;

@Mapper
@Repository
public interface UserMapper {
    UserDTO getUserById(String id);
}
