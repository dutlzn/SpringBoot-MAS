package com.project.controller;

import com.project.dto.Result;
import com.project.dto.RoleUserDto;
import com.project.mapper.RoleUserMapper;
import com.project.service.RoleUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "角色用户关联")
@RestController
@RequestMapping("/admin/roleUser")
public class RoleUserController {
    @Autowired
    private RoleUserMapper roleUserMapper;
    @Autowired
    private RoleUserService roleUserService;


//    @ApiOperation(value = "根据用户id查询对应的角色")
//    @GetMapping("/role/{userId}")
//    public Result getRole(@PathVariable int userId) {
//        return Result.ok(roleUserService.getRole(userId),"查询成功");
//    }

//    @ApiOperation(value = "根据角色id加载用户列表")
//    @GetMapping("/list-user/{roleId}")
//    public Result listUsers(@PathVariable int roleId) {
//        return Result.ok(roleUserService.listUser(roleId), "查询成功");
//    }

    @ApiOperation(value = "保存用户角色")
    @PostMapping("/save")
    public Result save(@RequestBody RoleUserDto roleUserDto) {
        roleUserService.save(roleUserDto);
        return Result.ok("保存成功");
    }
}
