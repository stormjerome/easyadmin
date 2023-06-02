package com.zhxin.logic.system.model;

import java.util.Date;

/**
 * @ClassName SysAdminLoginLog
 * @Description //SysAdminLoginLog
 * @Author singleZhang
 * @Email 405780096@qq.com
 * @Date 2020/11/23 0023 下午 4:18
 **/
public class SysAdminLoginLog extends BaseModel{
    private static final long serialVersionUID = -1665789170200277868L;
    private Long logId;
    private String userName;
    private String loginIp;
    private String loginLocation;
    private String userAgent;
    private Date loginTime;

    private SysAdminUser user;

    public Long getLogId() {
        return logId;
    }

    public void setLogId(Long logId) {
        this.logId = logId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    public String getLoginLocation() {
        return loginLocation;
    }

    public void setLoginLocation(String loginLocation) {
        this.loginLocation = loginLocation;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public SysAdminUser getUser() {
        return user;
    }

    public void setUser(SysAdminUser user) {
        this.user = user;
    }
}
