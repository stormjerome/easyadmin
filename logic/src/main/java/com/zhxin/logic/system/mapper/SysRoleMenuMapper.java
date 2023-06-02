package com.zhxin.logic.system.mapper;

import com.zhxin.logic.system.model.SysRoleMenu;
import com.zhxin.logic.system.model.SysUserRole;

import java.util.List;

public interface SysRoleMenuMapper {
    int insert(SysUserRole record);
    int batchRoleMenu(List<SysRoleMenu> roleMenuList);
    int deleteRoleMenuByRoleId(Long roleId);
}