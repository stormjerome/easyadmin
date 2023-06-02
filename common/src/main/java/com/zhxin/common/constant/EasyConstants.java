package com.zhxin.common.constant;

import io.jsonwebtoken.Claims;

/**
 * @ClassName EasyConstants
 * @Description //系统通用常量信息
 * @Author singleZhang
 * @Email 405780096@qq.com
 * @Date 2020/10/20 0020 下午 3:31
 **/
public class EasyConstants {
    /**
     * LOCAL_IP
     * */
    public static final String LOCAL_IP = "127.0.0.1";
    /**
     * UTF-8 字符集
     */
    public static final String UTF8 = "UTF-8";

    /**
     * 默认页码
     * */
    public static final Integer DEFAULT_PAGE_NUM = 1;

    /**
     * 默认每页记录数
     * */
    public static final Integer DEFAULT_PAGE_SIZE = 20;

    /**
     * 验证码 redis key
     */
    public static final String CAPTCHA_CODE_KEY = "captcha_code:";

    /**
     * 验证码有效期(分钟)
     */
    public static final Integer CAPTCHA_EXPIRES_IN = 2;
    public static final int DEFAULT_CAPTCHA_WIDTH = 135;
    public static final int DEFAULT_CAPTCHA_HEIGHT = 40;
    public static final String FORM_TOKEN_KEY = "form_token";

    /**
     * LOGIN JWT TOKEN
     */
    public static final String TOKEN_HEADER = "Authorization";
    public static final String LOGIN_TOKEN_KEY = "login_token:";
    public static final String TOKEN = "token";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String LOGIN_USER_KEY = "login_user_key";
    public static final String JWT_USERID = "userid";
    public static final String JWT_USERNAME = Claims.SUBJECT;
    public static final String JWT_AVATAR = "avatar";
    public static final String JWT_CREATED = "created";
    public static final String JWT_AUTHORITIES = "authorities";

    /**
     * tree menu
     * */
    public static final String LEFT_HTML = "|—";

    /**
     * 通用status状态码
     * 0:未启用
     * 1:正常
     */
    public static final Integer COMMON_STATUS_CLOSE = 0;
    public static final Integer COMMON_STATUS_OPEN = 1;
}
