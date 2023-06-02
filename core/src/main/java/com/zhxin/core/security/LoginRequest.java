package com.zhxin.core.security;

/**
 * @ClassName LoginRequest
 * @Description //用户登录请求参数
 * @Author singleZhang
 * @Email 405780096@qq.com
 * @Date 2020/10/21 0021 下午 2:14
 **/
public class LoginRequest {
    private String username;
    private String password;
    private String code;
    private String uuid;
    private String form_token;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getForm_token() {
        return form_token;
    }

    public void setForm_token(String form_token) {
        this.form_token = form_token;
    }
}
