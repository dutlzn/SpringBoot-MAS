package com.project.service;

import com.github.pagehelper.PageHelper;
import com.project.controller.EditUserDto;
import com.project.dto.PageDto;
import com.project.dto.UserDto;
import com.project.entity.RoleUser;
import com.project.entity.RoleUserExample;
import com.project.entity.SysUser;
import com.project.entity.SysUserExample;
import com.project.exception.CustomException;
import com.project.mapper.RoleUserMapper;
import com.project.mapper.SysUserMapper;
import com.project.utils.CopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 用户 服务类
 */
@Service
public class SysUserService {
    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private RoleUserMapper roleUserMapper;

    /**
     * 用户分页查询
     */
    public void list(PageDto<UserDto> pageDto) {
        int page = pageDto.getPage();
        int size = pageDto.getSize();
        List<SysUser> queryList = new ArrayList<>();
        List<UserDto> result = new ArrayList<>();
        SysUserExample sysUserExample = new SysUserExample();
        if (size == -1) {
            // 全部数据
            queryList = sysUserMapper.selectByExample(sysUserExample);
            pageDto.setTotal((int) sysUserMapper.countByExample(sysUserExample));
            UserDto userDto = null;
            for (SysUser sysUser : queryList) {
                userDto = CopyUtils.copy(sysUser, UserDto.class);
                result.add(userDto);
            }
            pageDto.setList(result);
            return ;
        } else {
            PageHelper.startPage(page, size);
            queryList = sysUserMapper.selectByExample(sysUserExample);
            UserDto userDto = null;
            for (SysUser sysUser : queryList) {
                userDto = CopyUtils.copy(sysUser, UserDto.class);
                result.add(userDto);
            }
//            PageInfo<SysUser> pageInfo = new PageInfo<>(queryList);
//            System.out.println(pageInfo.getTotal());
            pageDto.setTotal((int) sysUserMapper.countByExample(sysUserExample));
            pageDto.setList(result);
        }


    }

    /**
     * 编辑或者修改
     * @param editUserDto
     */
    public void save(EditUserDto editUserDto) {
        SysUser sysUser = CopyUtils.copy(editUserDto, SysUser.class);
        if (editUserDto.getId() == null ||
                editUserDto.getId().equals(0)) {
            // 新增
            // 新增之前判断有没有这个用户
            SysUserExample sysUserExample = new SysUserExample();
            sysUserExample.createCriteria().andUserNameEqualTo(sysUser.getUserName());
            List<SysUser> sysUsers = sysUserMapper.selectByExample(sysUserExample);
            if(sysUsers.size() != 0) throw new CustomException("该用户名已经被注册!");
            // 设置创建时间
            sysUser.setCreateTime(new Date());
            sysUserMapper.insert(sysUser);
            sysUsers = sysUserMapper.selectByExample(sysUserExample);
            // 新增用户 默认角色为普通用户
            RoleUser roleUser = new RoleUser();
            roleUser.setRoleId(5);
            roleUser.setUserId(sysUsers.get(0).getId());
            roleUserMapper.insert(roleUser);
        } else {
            // 编辑
            sysUserMapper.updateByPrimaryKeySelective(sysUser);
        }
    }

    public void delete(int id) {
        // 不仅要删除用户 还要删除对应的用户-角色
        RoleUserExample roleUserExample = new RoleUserExample();
        roleUserExample.createCriteria().andUserIdEqualTo(id);
        roleUserMapper.deleteByExample(roleUserExample);
        // 最后删除用户
        sysUserMapper.deleteByPrimaryKey(id);
    }

    public List<UserDto> all() {
        List<SysUser> userList = sysUserMapper.selectByExample(new SysUserExample());
        List<UserDto> result = new ArrayList<>();
        UserDto userDto = null;
        for (SysUser sysUser : userList) {
            userDto = CopyUtils.copy(sysUser, UserDto.class);
            // 查询角色id
            RoleUserExample roleUserExample = new RoleUserExample();
            roleUserExample.createCriteria().andUserIdEqualTo(sysUser.getId());
            List<RoleUser> roleUsers = roleUserMapper.selectByExample(roleUserExample);
            userDto.setRoleId(roleUsers.get(0).getRoleId());
            result.add(userDto);
        }
        return result;
    }
}
