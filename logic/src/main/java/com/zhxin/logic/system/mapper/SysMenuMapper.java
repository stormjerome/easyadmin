package com.zhxin.logic.system.mapper;

import com.zhxin.logic.system.model.SysMenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysMenuMapper {
    int insertMenu(SysMenu record);

    List<String> selectMenuPermsByUserId(Long userId);

    List<SysMenu> selectMenuList(SysMenu menu);

    /**
     * 管理员获取全部权限
     * */
    List<SysMenu> selectAllMenuForAdmin();

    List<SysMenu> selectMenuByUserId(Long userId);

    List<String> selectMenuByRoleId(Long roleId);

    int updateMenu(SysMenu menu);
    int softDeleteMenu(SysMenu menu);

    SysMenu checkMenuData(@Param("menuName") String menuName, @Param("parentId") Long parentId);
    SysMenu selectMenuById(Long menuId);
}