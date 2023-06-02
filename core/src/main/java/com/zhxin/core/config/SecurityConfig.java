package com.zhxin.core.config;

import com.zhxin.core.security.filter.SecurityFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;

/**
 * @ClassName SecurityConfig
 * @Description //security配置类
 * @Author singleZhang
 * @Email 405780096@qq.com
 * @Date 2020/10/14 0014 下午 3:50
 **/
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthenticationSuccessHandler authenticationSuccessHandler;
    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;
    @Autowired
    private AuthenticationEntryPoint authenticationEntryPoint;
    @Autowired
    private AccessDeniedHandler accessDeniedHandler;

    /**
     * 自定义用户认证逻辑
     */
    @Autowired
    private UserDetailsService userDetailsService;

    /**
     * 退出处理类
     */
    @Autowired
    private LogoutSuccessHandler logoutSuccessHandler;

    /**
     * token认证过滤器
     */
    @Autowired
    private SecurityFilter securityFilter;

    /**
     * 自定义安全配置和认证配置
     * */
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception{

        httpSecurity
                .csrf().disable();
                //.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        httpSecurity.authorizeRequests()
                // 对于登录login 验证码captchaImage 允许匿名访问
                //.antMatchers("/login", "/captcha").anonymous()
                .antMatchers(HttpMethod.GET,
                        "/hello","/captcha","/","/favicon.ico", "/css/**", "/js/**", "/img/**","/fonts/**", "/admin/**","/plugins/**",
                        "/templates/**","/swagger-resources/**","/webjars/**","/druid/**"
                ).permitAll().anyRequest().authenticated()
                .and()
                .headers().frameOptions().disable();

        httpSecurity.formLogin()
                .loginPage("/login")
                .permitAll()
                .loginProcessingUrl("/login")
                .successHandler(authenticationSuccessHandler).
                failureHandler(authenticationFailureHandler).and()
                .exceptionHandling().authenticationEntryPoint(authenticationEntryPoint).accessDeniedHandler(accessDeniedHandler);

        // logout
        httpSecurity.logout().logoutUrl("/logout").logoutSuccessHandler(logoutSuccessHandler);
        // 添加JWT filter
        httpSecurity.addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class);

    }
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception
    {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception
    {
        return super.authenticationManagerBean();
    }

    /**
     * 强散列哈希加密实现
     */
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder()
    {
        return new BCryptPasswordEncoder();
    }
}
