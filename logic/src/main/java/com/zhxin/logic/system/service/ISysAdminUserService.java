package com.zhxin.logic.system.service;



import com.zhxin.logic.system.model.SysAdminUser;

import java.util.List;

public interface ISysAdminUserService {

    int insertUser(SysAdminUser user);

    /**
     * 后台用户列表
     * */
    List<SysAdminUser> selectUserList(SysAdminUser user);

    SysAdminUser getUserByName(String userName);
    SysAdminUser getUserById(Long userId);
    int updateUserSimple(SysAdminUser user);
    int updateUser(SysAdminUser user);
    Boolean checkUserByName(String userName);
}
