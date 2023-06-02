package com.zhxin.core.security.service;

import com.zhxin.common.constant.EasyConstants;
import com.zhxin.common.exception.GlobalException;
import com.zhxin.common.utils.RedisUtil;
import com.zhxin.common.utils.StringUtil;
import com.zhxin.core.security.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @ClassName LoginService
 * @Description //登录验证处理 TODO 废弃20201026
 * @Author singleZhang
 * @Email 405780096@qq.com
 * @Date 2020/10/21 0021 下午 3:23
 **/
//@Component
public class LoginService {

    @Autowired
    RedisUtil redisUtil;

    @Resource
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenService jwtTokenService;

    /**
     * 用户登录处理
     * */
    public String login(String username, String password, String code, String uuid){
        String verifyRedisKey = EasyConstants.CAPTCHA_CODE_KEY + uuid;
        String captcha = redisUtil.getCacheObject(verifyRedisKey);
        redisUtil.deleteObject(verifyRedisKey);
        if (StringUtil.isEmpty(captcha)) throw new GlobalException("user.captcha.expire","验证码过期");

        if (!code.equalsIgnoreCase(captcha)) throw new GlobalException("user.captcha.error","验证码错误");

        // Security用户验证
        Authentication authentication = null;
        try {
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        }catch (Exception e){
            throw new GlobalException("user.login.error","用户名或密码错误");
        }

        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        return jwtTokenService.createToken(loginUser);
    }

}
