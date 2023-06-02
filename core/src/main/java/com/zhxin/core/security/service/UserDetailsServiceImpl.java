package com.zhxin.core.security.service;

import com.zhxin.common.exception.GlobalException;
import com.zhxin.common.utils.StringUtil;
import com.zhxin.core.security.LoginUser;
import com.zhxin.logic.system.model.SysAdminUser;
import com.zhxin.logic.system.service.ISysAdminUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @ClassName UserDetailsServiceImpl
 * @Description //UserDetailsServiceImpl
 * @Author singleZhang
 * @Email 405780096@qq.com
 * @Date 2020/10/23 0023 上午 9:51
 **/
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private static final Logger log = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Autowired
    private ISysAdminUserService userService;

    @Autowired
    private PermissionService permissionService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        SysAdminUser user = userService.getUserByName(username);
        if (StringUtil.isNull(user)) {
            log.info("登录用户：{} 不存在.", username);
            throw new UsernameNotFoundException("登录用户：" + username + " 不存在");
        } else if ( SysAdminUser.Status.DELETED == user.getDeletedFlag()) {
            log.info("登录用户：{} 已被删除.", username);
            throw new GlobalException("对不起，您的账号：" + username + " 已被删除");
        } else if (SysAdminUser.Status.DISABLED == user.getStatus()) {
            log.info("登录用户：{} 已被停用.", username);
            throw new GlobalException("对不起，您的账号：" + username + " 已停用");
        }
        return new LoginUser(user, permissionService.getMenuPermission(user));
    }

}
