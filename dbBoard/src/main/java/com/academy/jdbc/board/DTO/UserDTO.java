package com.academy.jdbc.board.DTO;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserDTO {
    private int id;
    private String userName;
    private String password;
    private int role;
}
