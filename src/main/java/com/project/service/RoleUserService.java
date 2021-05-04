package com.project.service;

import com.project.dto.RoleUserDto;
import com.project.dto.UserDto;
import com.project.entity.RoleUser;
import com.project.entity.RoleUserExample;
import com.project.entity.SysUser;
import com.project.mapper.RoleUserMapper;
import com.project.mapper.SysUserMapper;
import com.project.utils.CopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleUserService {
    @Autowired
    private RoleUserMapper roleUserMapper;
    @Autowired
    private SysUserMapper sysUserMapper;

    /**
     * 根据用户id 查询 角色id
     * @param userId
     * @return
     */
    public RoleUserDto getRole(int userId) {
        RoleUserExample roleUserExample = new RoleUserExample();
        roleUserExample.createCriteria().andUserIdEqualTo(userId);
        List<RoleUser> roleUsers = roleUserMapper.selectByExample(roleUserExample);
        RoleUserDto roleUserDto = CopyUtils.copy(roleUsers.get(0), RoleUserDto.class);
        return roleUserDto;
    }

    /**
     * 根据角色id 返回用户列表
     * @param roleId
     * @return
     */
    public List<UserDto> listUser(int roleId) {
        List<UserDto> result = new ArrayList<>();
        RoleUserExample roleUserExample = new RoleUserExample();
        roleUserExample.createCriteria().andRoleIdEqualTo(roleId);
        List<RoleUser> list = roleUserMapper.selectByExample(roleUserExample);
        for (RoleUser roleUser : list) {
            int userId = roleUser.getUserId();
            SysUser sysUser = sysUserMapper.selectByPrimaryKey(userId);
            UserDto userDto = CopyUtils.copy(sysUser, UserDto.class);
            result.add(userDto);
        }
        return result;
    }

    public void save(RoleUserDto roleUserDto) {
        // 只需要编辑，默认是普通用户
        int userId = roleUserDto.getUserId();
        int roleId = roleUserDto.getRoleId();
        RoleUserExample roleUserExample = new RoleUserExample();
        roleUserExample.createCriteria().andUserIdEqualTo(userId);
        List<RoleUser> roleUsers = roleUserMapper.selectByExample(roleUserExample);
        RoleUser roleUser = roleUsers.get(0);
        roleUser.setRoleId(roleId);
        roleUserMapper.updateByPrimaryKeySelective(roleUser);
    }
}
