package com.zhxin.core.security.service;

import com.zhxin.logic.system.model.SysAdminUser;
import com.zhxin.logic.system.model.SysMenu;
import com.zhxin.logic.system.service.ISysMenuService;
import com.zhxin.logic.system.service.ISysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @ClassName SysPermissionService
 * @Description //用户权限处理
 * @Author singleZhang
 * @Email 405780096@qq.com
 * @Date 2020/10/23 0023 上午 10:34
 **/
@Service
public class PermissionService {

    @Autowired
    private ISysRoleService roleService;

    @Autowired
    private ISysMenuService menuService;


    /**
     * 获取角色数据权限
     *
     * @param user 用户信息
     * @return 角色权限信息
     */
    public Set<String> getRolePermission(SysAdminUser user)
    {
        Set<String> roles = new HashSet<>();
        if (user.getAdminUserId() == 1L) {
            roles.add("admin");
        } else {
            roles.addAll(roleService.selectRolePermissionByUserId(user.getAdminUserId()));
        }
        return roles;
    }

    /**
     * 获取菜单数据权限
     *
     * @param user 用户信息
     * @return 菜单权限信息
     */
    public List<SysMenu> getMenuPermission(SysAdminUser user)
    {
        List<SysMenu> perms;
        if (user.getAdminUserId() == 1L) {
            perms = menuService.selectAllMenuForAdmin();
        } else {
            perms = menuService.selectMenuByUserId(user.getAdminUserId());
        }
        return perms;
    }

}
