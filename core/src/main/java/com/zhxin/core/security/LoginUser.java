package com.zhxin.core.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zhxin.logic.system.model.SysAdminUser;
import com.zhxin.logic.system.model.SysMenu;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @ClassName LoginUser
 * @Description //Security UserDetail
 * @Author singleZhang
 * @Email 405780096@qq.com
 * @Date 2020/10/19 0019 下午 3:04
 **/
public class LoginUser implements UserDetails{

    private static final long serialVersionUID = 8143125624607840063L;

    /**
     * 用户唯一标识
     */
    private String token;

    /**
     * 登陆时间
     */
    private Long loginTime;

    /**
     * 过期时间
     */
    private Long expireTime;

    /**
     * 登录IP地址
     */
    private String ipaddr;

    /**
     * 登录地点
     */
    private String loginLocation;

    /**
     * 浏览器类型
     */
    private String browser;

    /**
     * 操作系统
     */
    private String os;

    /**
     * 权限列表
     */
    private List<SysMenu> permissions;

    private String username;

    /**
     * 用户信息
     */
    private SysAdminUser user;

    public LoginUser() {}

    public LoginUser(SysAdminUser user, List<SysMenu> permissions)
    {
        this.user = user;
        this.permissions = permissions;
    }

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities(){
        return permissions.parallelStream().filter(p -> !StringUtils.isEmpty(p.getPerms()))
                .map(p -> new SimpleGrantedAuthority(p.getPerms())).collect(Collectors.toSet());
    }

    @JsonIgnore
    @Override
    public String getPassword()
    {
        return user.getPassword();
    }

    @Override
    public String getUsername()
    {
        return user.getUserName();
    }

    /**
     * 账户是否未过期,过期无法验证
     */
    @JsonIgnore
    @Override
    public boolean isAccountNonExpired()
    {
        return true;
    }

    /**
     * 指定用户是否解锁,锁定的用户无法进行身份验证
     *
     * @return
     */
    @JsonIgnore
    @Override
    public boolean isAccountNonLocked()
    {
        return true;
    }

    /**
     * 指示是否已过期的用户的凭据(密码),过期的凭据防止认证
     *
     * @return
     */
    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired()
    {
        return true;
    }

    /**
     * 是否可用 ,禁用的用户不能身份验证
     *
     * @return
     */
    @JsonIgnore
    @Override
    public boolean isEnabled()
    {
        return user.getStatus().equals(SysAdminUser.Status.VALID);
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Long loginTime) {
        this.loginTime = loginTime;
    }

    public Long getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Long expireTime) {
        this.expireTime = expireTime;
    }

    public String getIpaddr() {
        return ipaddr;
    }

    public void setIpaddr(String ipaddr) {
        this.ipaddr = ipaddr;
    }

    public String getLoginLocation() {
        return loginLocation;
    }

    public void setLoginLocation(String loginLocation) {
        this.loginLocation = loginLocation;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public List<SysMenu> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<SysMenu> permissions) {
        this.permissions = permissions;
    }

    public SysAdminUser getUser() {
        return user;
    }

    public void setUser(SysAdminUser user) {
        this.user = user;
    }


}
