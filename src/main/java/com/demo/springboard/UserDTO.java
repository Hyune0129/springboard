package com.demo.springboard;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDTO {
    private int uid;
    private String id;
    private String name;
    private String password;
}
