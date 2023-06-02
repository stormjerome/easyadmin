package com.zhxin.common.utils;

import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName ResponseUtil
 * @Description // 服务端返回辅助类
 * @Author singleZhang
 * @Email 405780096@qq.com
 * @Date 2020/10/19 0019 下午 2:03
 **/
public class ResponseUtil {

    /**
     * 返回JSON
     * */
    public static void responseJson(HttpServletResponse response, int status, Object data) {
        try {
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Access-Control-Allow-Methods", "*");
            response.setContentType("application/json;charset=UTF-8");
            response.setStatus(status);

            response.getWriter().write(JSONObject.toJSONString(data));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
