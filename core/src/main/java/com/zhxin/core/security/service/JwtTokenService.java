package com.zhxin.core.security.service;

import com.zhxin.common.constant.EasyConstants;
import com.zhxin.common.utils.EasyUtil;
import com.zhxin.common.utils.RedisUtil;
import com.zhxin.common.utils.ServletUtil;
import com.zhxin.common.utils.StringUtil;
import com.zhxin.core.security.LoginUser;
import eu.bitwalker.useragentutils.UserAgent;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName JwtTokenService
 * @Description //TODO
 * @Author singleZhang
 * @Email 405780096@qq.com
 * @Date 2020/10/23 0023 上午 8:46
 **/
@Component
public class JwtTokenService {
    // 令牌自定义标识
    @Value("${token.header}")
    private String header;

    // 令牌秘钥
    @Value("${token.jwtSecret}")
    private String secret;

    // 令牌有效期（默认30分钟）
    @Value("${token.expire}")
    private int expireTime;

    protected static final long MILLIS_SECOND = 1000;

    protected static final long MILLIS_MINUTE = 60 * MILLIS_SECOND;

    private static final Long MILLIS_MINUTE_TEN = 20 * 60 * 1000L;

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 获取用户身份信息
     *
     * @return 用户信息
     */
    public LoginUser getLoginUser(String token)
    {
        if (StringUtil.isNotEmpty(token)) {
            String uuid = getUUIDFromToken(token);
            String userKey = getTokenKey(uuid);
            LoginUser user = redisUtil.getCacheObject(userKey);
            return user;
        }
        return null;
    }


    /**
     * 生成token
     * */
    public String createToken(LoginUser loginUser){
        String token = UUID.randomUUID().toString();
        loginUser.setToken(token);
        setUserAgent(loginUser);
        refreshToken(loginUser);

        Map<String, Object> claims = new HashMap<>();
        claims.put(EasyConstants.LOGIN_USER_KEY, token);
        return generateToken(claims);
    }


    /**
     * 验证token,不足20分钟刷新token
     * */
    public void verifyToken(LoginUser loginUser){
        long expireTime = loginUser.getExpireTime();
        long currentTime = System.currentTimeMillis();
        if (expireTime - currentTime <= MILLIS_MINUTE_TEN) {
            refreshToken(loginUser);
        }
    }

    /**
     * 刷新令牌有效期
     *
     * @param loginUser 登录信息
     */
    public void refreshToken(LoginUser loginUser)
    {
        loginUser.setLoginTime(System.currentTimeMillis());
        loginUser.setExpireTime(loginUser.getLoginTime() + expireTime * MILLIS_MINUTE);

        String userKey = getTokenKey(loginUser.getToken());
        redisUtil.setCacheObject(userKey, loginUser, expireTime, TimeUnit.MINUTES);
    }

    public boolean deleteToken(String jwtToken) {
        String uuid = getUUIDFromToken(jwtToken);
        if (uuid != null) {
            String key = getTokenKey(uuid);
            LoginUser loginUser = redisUtil.getCacheObject(key);
            if (loginUser != null) {
                redisUtil.deleteObject(key);
                return true;
            }
        }
        return false;
    }

    /**
     * 设置用户代理信息
     *
     * @param loginUser 登录信息
     */
    public void setUserAgent(LoginUser loginUser)
    {
        UserAgent userAgent = UserAgent.parseUserAgentString(ServletUtil.getRequest().getHeader("User-Agent"));
        String ip = EasyUtil.getIpAddr(ServletUtil.getRequest());
        loginUser.setIpaddr(ip);
        loginUser.setLoginLocation(EasyUtil.getRealAddressByIP(ip));
        loginUser.setBrowser(userAgent.getBrowser().getName());
        loginUser.setOs(userAgent.getOperatingSystem().getName());
    }

    /**
     * 从令牌中获取数据声明
     *
     * @param token 令牌
     * @return 数据声明
     */
    private Claims parseToken(String token)
    {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * 从数据声明生成令牌
     *
     * @param claims 数据声明
     * @return 令牌
     */
    private String generateToken(Map<String, Object> claims)
    {
        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    /**
     * 从令牌中获取用户名
     *
     * @param token 令牌
     * @return 用户名
     */
    private String getUUIDFromToken(String token)
    {
        if ("null".equals(token) || StringUtils.isBlank(token)) {
            return "";
        }
        Claims claims = parseToken(token);
        return (String) claims.get(EasyConstants.LOGIN_USER_KEY);
    }

    private String getTokenKey(String uuid)
    {
        return EasyConstants.LOGIN_TOKEN_KEY + uuid;
    }
}
