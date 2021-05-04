package com.project.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.entity.SysUser;
import lombok.Data;

import java.util.Date;

@Data
public class UserDto {
    private Integer id;

    private String userName;

    private String userPassword;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date lastLoginTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;

    private Integer roleId;


}
