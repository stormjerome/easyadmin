package com.zhxin.logic.system.service;

import com.zhxin.logic.system.model.SysRole;

import java.util.List;
import java.util.Set;

public interface ISysRoleService {

    /**
     * 根据用户ID查询角色权限
     */
    Set<String> selectRolePermissionByUserId(Long userId);

    List<SysRole> selectRole(SysRole role);
    List<SysRole> selectRolesByUserId(Long userId);
    SysRole selectRoleById(Long roleId);
    int insertRole(SysRole role);
    int updateRole(SysRole role);
    int updateRoleSimple(SysRole role);
    int removeRole(SysRole role);
}
