package com.zhxin.core.security.handle;

import com.zhxin.common.utils.ResponseUtil;
import com.zhxin.common.utils.StringUtil;
import com.zhxin.core.execute.AsyncExecutor;
import com.zhxin.core.execute.task.LogTask;
import com.zhxin.core.security.LoginUser;
import com.zhxin.core.security.filter.SecurityFilter;
import com.zhxin.core.security.service.JwtTokenService;
import com.zhxin.core.web.model.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName SecurityHandlerConfig
 * @Description //处理类配置
 * @Author singleZhang
 * @Email 405780096@qq.com
 * @Date 2020/10/26 0026 上午 9:36
 **/
@Configuration
public class SecurityHandlerConfig {

    @Autowired
    private JwtTokenService tokenService;

    /**
     * 登陆成功，返回Token
     *
     * @return
     */
    @Bean
    public AuthenticationSuccessHandler loginSuccessHandler() {
        return new AuthenticationSuccessHandler() {

            @Override
            public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                                Authentication authentication) {
                LoginUser loginUser = (LoginUser) authentication.getPrincipal();
                String token = tokenService.createToken(loginUser);
                AsyncExecutor.build().execute(LogTask.addLoginLog(loginUser.getUser(), "Login Success"));
                ResponseVo result = ResponseVo.success("登录成功",token);
                ResponseUtil.responseJson(response, HttpStatus.OK.value(), result);

            }
        };
    }


    /**
     * 登陆失败
     *
     * @return
     */
    @Bean
    public AuthenticationFailureHandler loginFailureHandler() {
        return new AuthenticationFailureHandler() {

            @Override
            public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                                AuthenticationException exception) {
                String msg = exception instanceof BadCredentialsException ? "用户名或密码错误" : exception.getMessage();
                ResponseVo result = ResponseVo.error(HttpStatus.UNAUTHORIZED.value(),msg);
                ResponseUtil.responseJson(response, HttpStatus.UNAUTHORIZED.value(), result);
            }
        };

    }

    /**
     * 未登录，返回401
     *
     * @return
     */
    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return new AuthenticationEntryPoint() {

            @Override
            public void commence(HttpServletRequest request, HttpServletResponse response,
                                 AuthenticationException authException) throws IOException {
                if(StringUtil.isNotEmpty(request.getHeader("X-Requested-With"))){
                    ResponseVo result = ResponseVo.error(HttpStatus.UNAUTHORIZED.value(),"还未登录");
                    ResponseUtil.responseJson(response, HttpStatus.UNAUTHORIZED.value(), result);
                }else{
                    response.sendRedirect("/login");
                }

            }
        };
    }

    /**
     * 退出处理
     *
     * @return
     */
    @Bean
    public LogoutSuccessHandler logoutSussHandler() {
        return new LogoutSuccessHandler() {

            @Override
            public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {


                String token = SecurityFilter.getToken(request);
                tokenService.deleteToken(token);

                if(StringUtil.isNotEmpty(request.getHeader("X-Requested-With"))){
                    ResponseVo info = ResponseVo.success("退出成功");
                    ResponseUtil.responseJson(response, HttpStatus.OK.value(), info);
                }else{
                    response.sendRedirect("/login");
                }

            }
        };

    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler(){
        return new AccessDeniedHandler() {
            @Override
            public void handle(HttpServletRequest request, HttpServletResponse response,
                               AccessDeniedException e) throws IOException, ServletException {

                if(StringUtil.isNotEmpty(request.getHeader("X-Requested-With"))){
                    ResponseVo result = ResponseVo.error(HttpStatus.FORBIDDEN.value(),"没有权限");
                    ResponseUtil.responseJson(response, HttpStatus.FORBIDDEN.value(), result);
                }else{
                    //TODO 跳转到403页面
                }

            }
        };
    }


}
