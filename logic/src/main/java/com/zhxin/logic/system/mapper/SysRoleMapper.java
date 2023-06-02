package com.zhxin.logic.system.mapper;

import com.zhxin.logic.system.model.SysRole;

import java.util.List;

public interface SysRoleMapper {
    int insert(SysRole record);

    int insertSelective(SysRole record);

    List<SysRole> selectRole(SysRole role);
    List<SysRole> selectRolePermissionByUserId(Long userId);
    List<SysRole> selectRolesByUserId(Long userId);
    SysRole selectRoleById(Long roleId);
    int insertRole(SysRole role);
    int updateRole(SysRole role);
    int softDeleteRole(SysRole role);
}