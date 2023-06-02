package com.zhxin.common.utils;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.MapType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.sun.corba.se.impl.ior.OldJIDLObjectKeyTemplate;
import com.zhxin.common.constant.EasyConstants;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ClassName EasyUtil
 * @Description //通用工具类
 * @Author singleZhang
 * @Email 405780096@qq.com
 * @Date 2020/10/21 0021 上午 9:17
 **/
public class EasyUtil {

    /**
     * 生成唯一字符串
     * */
    public static String uuid(Integer len){
        Date now = new Date();
        StringBuffer randomStr = new StringBuffer(RandomStringUtils.random(len,'0','z',true, true));
        randomStr.append(DateFormatUtils.format(now, "yyyyMMddHHmmss"));
        return randomStr.toString();
    }

    public static String getHostIp() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            //
        }
        return "127.0.0.1";
    }

    /**
     * 获取客户端IP地址
     * */
    public static String getIpAddr(HttpServletRequest request) {
        if (request == null) {
            return "unknown";
        }
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
        {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
        {
            ip = request.getHeader("X-Forwarded-For");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
        {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
        {
            ip = request.getHeader("X-Real-IP");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
        {
            ip = request.getRemoteAddr();
        }

        return "0:0:0:0:0:0:0:1".equals(ip) ? "127.0.0.1" : ip;
    }

    /**
     * 获取IP区域 TODO
     * */
    public static String getRealAddressByIP(String ip) {


        final String IP_URL = "http://whois.pconline.com.cn/ipJson.jsp";
        String address = "内网IP" ;

        // 内网不查询
        if (EasyConstants.LOCAL_IP.equals(ip)) {
            return "内网IP" ;
        }

        String rspStr = HttpUtil.post(IP_URL, "ip=" + ip +"&json=true");
        if (StringUtil.isEmpty(rspStr)) {
            return address;
        }

        JSONObject obj = JSONObject.parseObject(rspStr);
        String pro = obj.getString("pro");
        String city = obj.getString("city");
        address = pro + " " + city;

        return address;
    }

    /**
     *
     */
    public static String logBuffer(Object obj){
        if (obj == null) {
            obj = "";
        }
        return "[" + obj.toString() + "]";
    }

    /**
     * 生成BCryptPasswordEncoder密码
     *
     * @param password 密码
     * @return 加密字符串
     */
    public static String encryptPassword(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }

    /**
     * 获取Authentication
     */
    public static Authentication getAuthentication()
    {
        return SecurityContextHolder.getContext().getAuthentication();
    }

}
