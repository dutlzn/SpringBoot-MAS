package com.project.controller;

import com.project.dto.Result;
import com.project.entity.SysRoleExample;
import com.project.mapper.SysRoleMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "角色管理")
@RestController
@RequestMapping("/admin/role")
public class RoleController {
    @Autowired
    private SysRoleMapper sysRoleMapper;

    @ApiOperation(value = "查询所有角色")
    @GetMapping("/all")
    public Result allRoles() {
        return Result.ok(sysRoleMapper.selectByExample(new SysRoleExample()), "查询成功");
    }
}
