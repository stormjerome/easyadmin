package com.zhxin.logic.system.model;

/**
 * @ClassName SysRoleMenu
 * @Description //TODO
 * @Author singleZhang
 * @Email 405780096@qq.com
 * @Date 2020/10/19 0019 下午 4:42
 **/
public class SysRoleMenu {
    /** 角色ID */
    private Long roleId;

    /** 菜单ID */
    private Long menuId;

    public Long getRoleId()
    {
        return roleId;
    }

    public void setRoleId(Long roleId)
    {
        this.roleId = roleId;
    }

    public Long getMenuId()
    {
        return menuId;
    }

    public void setMenuId(Long menuId)
    {
        this.menuId = menuId;
    }
}
