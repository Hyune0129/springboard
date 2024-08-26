package com.demo.springboard.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.demo.springboard.BoardDTO;

@Mapper
@Repository
public interface BoardMapper {

    List<BoardDTO> getBoards();
}
