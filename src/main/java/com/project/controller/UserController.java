package com.project.controller;

import com.project.dto.PageDto;
import com.project.dto.Result;
import com.project.dto.UserDto;
import com.project.entity.SysUserExample;
import com.project.mapper.SysUserMapper;
import com.project.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "用户管理")
@RestController
@RequestMapping("/admin/user")
public class UserController {
    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private SysUserService sysUserService;

    /**
     * 用户列表查询（不分页）
     */
    @ApiOperation(value = "查询用户（不分页）")
    @GetMapping("/all")
    public Result getUserList(){
        return Result.ok(sysUserService.all(), "查询成功");
    }
//
//    /**
//     * 用户列表查询（分页）
//     * @param pageDto
//     * @return
//     */
//    @ApiOperation(value = "查询用户（分页）")
//    @PostMapping("/list")
//    public Result pageUser(@RequestBody PageDto pageDto) {
//        Result result = new Result();
//        sysUserService.list(pageDto);
//        return result.ok(pageDto);
//    }

    /**
     * 新增、编辑用户
     */
    @ApiOperation(value = "保存用户（含新增、编辑）")
    @PostMapping("/save")
    public Result save(@RequestBody EditUserDto editUserDto) {
        sysUserService.save(editUserDto);
        return Result.ok("保存成功");
    }
    @ApiOperation(value = "删除用户")
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable int id) {
        sysUserService.delete(id);
        return Result.ok("删除成功");
    }
}
