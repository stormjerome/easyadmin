package com.zhxin.logic.system.service.impl;


import com.zhxin.common.utils.StringUtil;
import com.zhxin.logic.system.mapper.SysAdminUserMapper;
import com.zhxin.logic.system.mapper.SysUserRoleMapper;
import com.zhxin.logic.system.model.SysAdminUser;
import com.zhxin.logic.system.model.SysRoleMenu;
import com.zhxin.logic.system.model.SysUserRole;
import com.zhxin.logic.system.service.ISysAdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName SysAdminUserServiceImpl
 * @Description //SysAdminUserServiceImpl
 * @Author singleZhang
 * @Email 405780096@qq.com
 * @Date 2020/10/20 0020 上午 9:35
 **/
@Service
public class SysAdminUserServiceImpl implements ISysAdminUserService {

    @Autowired
    SysAdminUserMapper sysAdminUserMapper;

    @Autowired
    SysUserRoleMapper sysUserRoleMapper;

    @Override
    public int insertUser(SysAdminUser user){
        sysAdminUserMapper.insertUser(user);
        return insertUserRole(user);
    }

    @Override
    public List<SysAdminUser> selectUserList(SysAdminUser user)
    {
        return sysAdminUserMapper.selectUserList(user);
    }


    @Override
    public SysAdminUser getUserByName(String username)
    {
        return sysAdminUserMapper.getUserByName(username);
    }

    @Override
    public int updateUserSimple(SysAdminUser user){
        return sysAdminUserMapper.updateUser(user);
    }

    @Override
    public int updateUser(SysAdminUser user){
        Long userId = user.getAdminUserId();
        sysUserRoleMapper.deleteUserRoleByUserId(userId);
        insertUserRole(user);
        return sysAdminUserMapper.updateUser(user);

    }

    @Override
    public  SysAdminUser getUserById(Long userId){
        return sysAdminUserMapper.getUserById(userId);
    }

    @Override
    public Boolean checkUserByName(String userName){
        SysAdminUser info = getUserByName(userName);
        System.out.println(info);
        return StringUtil.isNull(info);
    }

    /**
     * 批量用户-角色
     * */
    private int insertUserRole(SysAdminUser user){
        int rows = 1;
        List<SysUserRole> list = new ArrayList<>();
        for (Long roleId : user.getRoleIds()) {
            if(StringUtil.isNull(roleId)) continue;
            SysUserRole ur = new SysUserRole();
            ur.setRoleId(roleId);
            ur.setUserId(user.getAdminUserId());
            list.add(ur);
        }
        if (!CollectionUtils.isEmpty(list)) {
            rows = sysUserRoleMapper.batchUserRole(list);
        }
        return rows;
    }
}
