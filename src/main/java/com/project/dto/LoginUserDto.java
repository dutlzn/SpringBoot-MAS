package com.project.dto;

import lombok.Data;

@Data
public class LoginUserDto {
//    private int id;
    private String userName;
    private String userPassword;
    private int roleId;
}
