package com.zhxin.logic.system.service;

import com.zhxin.common.web.ZtreeVo;
import com.zhxin.logic.system.model.SysMenu;
import com.zhxin.logic.system.model.SysRole;

import java.util.List;
import java.util.Set;

public interface ISysMenuService {

    int insertMenu(SysMenu menu);
    Set<String> selectMenuPermsByUserId(Long userId);

    List<SysMenu> selectAllMenu(SysMenu menu);
    List<SysMenu> selectAllMenuForAdmin();
    List<SysMenu> selectMenuByUserId(Long userId);
    SysMenu selectMenuById(Long menuId);

    int updateMenu(SysMenu menu);
    int removeMenu(SysMenu menu);
    List<SysMenu> selectMenuAll(Long userId);
    Boolean checkMenuData(SysMenu menu);
    List<ZtreeVo> treeData(SysRole role,Long userId);
}
