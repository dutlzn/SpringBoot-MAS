package com.project.controller;

import com.project.dto.LoginUserDto;
import com.project.dto.Result;
import com.project.entity.RoleUser;
import com.project.entity.RoleUserExample;
import com.project.entity.SysUser;
import com.project.entity.SysUserExample;
import com.project.exception.CustomException;
import com.project.mapper.RoleUserMapper;
import com.project.mapper.SysUserMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@Api(tags = "登录管理")
@RestController
@RequestMapping("/admin")
public class LoginController {
    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private RoleUserMapper roleUserMapper;

    @ApiOperation(value = "登录（前端提供用户名、密码），返回信息包括了角色id")
    @PostMapping("/login")
    public Result login(@RequestBody LoginUserDto loginUserDto){
        String password = loginUserDto.getUserPassword();
        String userName = loginUserDto.getUserName();

        if (userName == null || userName.length() == 0 || password == null || password.length() == 0) {
            throw new CustomException("用户名密码不能为空");
        }

        SysUserExample sysUserExample = new SysUserExample();
        sysUserExample.createCriteria().andUserNameEqualTo(userName);
        List<SysUser> sysUsers = sysUserMapper.selectByExample(sysUserExample);
        if(sysUsers.size() == 0) throw new CustomException("用户不存在!");
        if(!sysUsers.get(0).getUserPassword().equals(password)) throw new CustomException("密码错误!");

        RoleUserExample roleUserExample = new RoleUserExample();
        roleUserExample.createCriteria().andUserIdEqualTo(sysUsers.get(0).getId());
        List<RoleUser> roleUsers = roleUserMapper.selectByExample(roleUserExample);
        loginUserDto.setRoleId(roleUsers.get(0).getRoleId());
        // 设置登录时间
        sysUsers.get(0).setLastLoginTime(new Date());
        sysUserMapper.updateByPrimaryKeySelective(sysUsers.get(0));

        return Result.ok(loginUserDto, "登录成功");
    }
}
