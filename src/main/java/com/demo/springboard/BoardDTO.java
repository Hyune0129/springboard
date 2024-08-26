package com.demo.springboard;

import lombok.Data;

@Data
public class BoardDTO {
    int bid;
    String title;
    String time;
    String writer;
    // String description;
}
