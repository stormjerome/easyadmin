package com.zhxin.core.security.util;

import com.zhxin.common.exception.GlobalException;
import com.zhxin.core.security.LoginUser;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @ClassName UserUtil
 * @Description //UserUtil
 * @Author singleZhang
 * @Email 405780096@qq.com
 * @Date 2020/10/23 0023 下午 4:10
 **/
public class UserUtil {
    /**
     * 获取当前用户
     **/
    public static LoginUser getLoginUser() {
        Authentication authentication = getAuthentication();
        try{
            return (LoginUser) authentication.getPrincipal();
        }catch (Exception e){
            throw new GlobalException("获取用户信息异常", HttpStatus.UNAUTHORIZED.value());
        }
    }

    /**
     * 获取Authentication
     */
    public static Authentication getAuthentication()
    {
        return SecurityContextHolder.getContext().getAuthentication();
    }
}
