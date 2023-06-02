package com.zhxin.logic.system.mapper;

import com.zhxin.logic.system.model.SysUserRole;

import java.util.List;

public interface SysUserRoleMapper {
    int insert(SysUserRole record);

    int insertSelective(SysUserRole record);

    int deleteUserRoleByUserId(Long userId);
    int batchUserRole(List<SysUserRole> userRoleList);
}