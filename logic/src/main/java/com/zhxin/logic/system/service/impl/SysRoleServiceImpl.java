package com.zhxin.logic.system.service.impl;

import com.zhxin.common.utils.StringUtil;
import com.zhxin.logic.system.mapper.SysRoleMapper;
import com.zhxin.logic.system.mapper.SysRoleMenuMapper;
import com.zhxin.logic.system.model.SysRole;
import com.zhxin.logic.system.model.SysRoleMenu;
import com.zhxin.logic.system.service.ISysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * @ClassName SysRoleServiceImpl
 * @Description //SysRoleServiceImpl
 * @Author singleZhang
 * @Email 405780096@qq.com
 * @Date 2020/10/23 0023 上午 10:38
 **/
@Service
public class SysRoleServiceImpl implements ISysRoleService {

    @Autowired
    SysRoleMapper sysRoleMapper;

    @Autowired
    SysRoleMenuMapper sysRoleMenuMapper;

    @Override
    public Set<String> selectRolePermissionByUserId(Long userId){
        List<SysRole> perms = sysRoleMapper.selectRolePermissionByUserId(userId);

        Set<String> permsSet = new HashSet<>();
        for (SysRole perm:perms) {
            if (StringUtil.isNotNull(perm)) {
                permsSet.addAll(Arrays.asList(perm.getRoleCode().trim().split(",")));
            }
        }
        return permsSet;
    }

    @Override
    public List<SysRole> selectRole(SysRole role){
        return sysRoleMapper.selectRole(role);
    }

    @Override
    public SysRole selectRoleById(Long roleId){
        return sysRoleMapper.selectRoleById(roleId);
    }

    /**
     * 获取用户角色
     * */
    @Override
    public List<SysRole> selectRolesByUserId(Long userId){
        List<SysRole> userRoleList = sysRoleMapper.selectRolesByUserId(userId);
        List<SysRole> roles = sysRoleMapper.selectRole(new SysRole());
        for (SysRole role : roles) {
            for (SysRole userRole : userRoleList) {
                if (role.getRoleId().equals(userRole.getRoleId())) {
                    role.setCheckedFlag(1);
                    break;
                }
            }
        }
        return roles;
    }

    @Override
    public int insertRole(SysRole role){

        sysRoleMapper.insertRole(role);
        return insertRoleMenu(role);
    }

    @Override
    public int updateRole(SysRole role){
        sysRoleMapper.updateRole(role);
        sysRoleMenuMapper.deleteRoleMenuByRoleId(role.getRoleId());
        return insertRoleMenu(role);
    }

    @Override
    public int updateRoleSimple(SysRole role){
        return sysRoleMapper.updateRole(role);
    }



    @Override
    public int removeRole(SysRole role){
        return sysRoleMapper.softDeleteRole(role);
    }


    /**
     * 新增角色菜单信息
     */
    private int insertRoleMenu(SysRole role) {
        int rows = 1;
        List<SysRoleMenu> list = new ArrayList<>();
        for (Long menuId : role.getMenuIds()) {
            SysRoleMenu rm = new SysRoleMenu();
            rm.setRoleId(role.getRoleId());
            rm.setMenuId(menuId);
            list.add(rm);
        }
        if (!CollectionUtils.isEmpty(list)) {
            rows = sysRoleMenuMapper.batchRoleMenu(list);
        }
        return rows;
    }
}
