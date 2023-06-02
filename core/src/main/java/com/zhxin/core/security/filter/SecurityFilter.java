package com.zhxin.core.security.filter;

import com.zhxin.common.constant.EasyConstants;
import com.zhxin.common.utils.EasyUtil;
import com.zhxin.common.utils.RedisUtil;
import com.zhxin.common.utils.ResponseUtil;
import com.zhxin.common.utils.StringUtil;
import com.zhxin.core.security.LoginUser;
import com.zhxin.core.security.service.JwtTokenService;
import com.zhxin.core.web.model.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName JwtTokenFilter
 * @Description //TODO
 * @Author singleZhang
 * @Email 405780096@qq.com
 * @Date 2020/10/19 0019 下午 3:00
 **/
@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenService tokenService;

    @Autowired
    RedisUtil redisUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        String token = getToken(request);
        //登录请求需要验证码 //request.getRequestURI().replaceFirst("/easyadmin","")
        if ("POST".equals(request.getMethod()) && "/login".equals(request.getRequestURI())) {
            String uuid = request.getParameter("uuid");
            String code = request.getParameter("code");
            String verifyRedisKey = EasyConstants.CAPTCHA_CODE_KEY + uuid;
            String captcha = redisUtil.getCacheObject(verifyRedisKey);

            if (StringUtil.isEmpty(captcha) || !code.equalsIgnoreCase(captcha)) {
                ResponseVo result = ResponseVo.error(HttpStatus.UNAUTHORIZED.value(),"验证码错误");
                ResponseUtil.responseJson(response, HttpStatus.UNAUTHORIZED.value(), result);
                return;
            }
        }

        LoginUser loginUser = tokenService.getLoginUser(token);
        if (StringUtil.isNotNull(loginUser) && StringUtil.isNull(EasyUtil.getAuthentication())) {
            tokenService.verifyToken(loginUser);
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginUser, null, loginUser.getAuthorities());
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }

        chain.doFilter(request, response);
    }

    /**
     * 获取请求token
     *
     * @param request
     * @return token
     */
    public static String getToken(HttpServletRequest request)
    {
        String token = request.getHeader(EasyConstants.TOKEN_HEADER);
        if (StringUtil.isNotEmpty(token) && token.startsWith(EasyConstants.TOKEN_PREFIX)) {
            token = token.replace(EasyConstants.TOKEN_PREFIX, "");
        }else{
            //没在header中,可能在请求parameter中
             token = request.getParameter(EasyConstants.TOKEN);
        }
        return token;
    }
}
