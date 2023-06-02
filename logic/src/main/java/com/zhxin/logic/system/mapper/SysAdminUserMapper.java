package com.zhxin.logic.system.mapper;

import com.zhxin.logic.system.model.SysAdminUser;

import java.util.List;

public interface SysAdminUserMapper {
    int insertUser(SysAdminUser user);

    List<SysAdminUser> selectUserList(SysAdminUser sysAdminUser);

    SysAdminUser getUserByName(String userName);
    SysAdminUser getUserById(Long userId);
    int updateUser(SysAdminUser user);
}