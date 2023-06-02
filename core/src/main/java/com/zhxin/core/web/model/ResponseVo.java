package com.zhxin.core.web.model;

import com.zhxin.common.utils.StringUtil;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.util.HashMap;

/**
 * @ClassName ResponseVo
 * @Description //统一返回类
 * @Author singleZhang
 * @Email 405780096@qq.com
 * @Date 2020/10/19 0019 下午 2:24
 **/
public class ResponseVo extends HashMap<String,Object> implements Serializable {
    private static final long serialVersionUID = 1L;

    /** 状态码 */
    public static final String CODE_TAG = "code";

    /** 返回内容 */
    public static final String MSG_TAG = "msg";

    /** 数据对象 */
    public static final String DATA_TAG = "data";

    public ResponseVo(){}

    public ResponseVo(int code,String msg){
        super.put(CODE_TAG,code);
        super.put(MSG_TAG,msg);
    }

    public ResponseVo(int code,String msg,Object data){
        super.put(CODE_TAG, code);
        super.put(MSG_TAG, msg);
        if (StringUtil.isNotNull(data))
        {
            super.put(DATA_TAG, data);
        }
    }

    /**
     * 返回成功消息
     *
     * @return 成功消息
     */
    public static ResponseVo success()
    {
        return ResponseVo.success("操作成功");
    }

    /**
     * 返回成功数据
     *
     * @return 成功消息
     */
    public static ResponseVo success(Object data)
    {
        return ResponseVo.success("操作成功", data);
    }

    /**
     * 返回成功消息
     *
     * @param msg 返回内容
     * @return 成功消息
     */
    public static ResponseVo success(String msg)
    {
        return ResponseVo.success(msg, null);
    }

    /**
     * 返回成功消息
     *
     * @param msg 返回内容
     * @param data 数据对象
     * @return 成功消息
     */
    public static ResponseVo success(String msg, Object data)
    {
        return new ResponseVo(HttpStatus.OK.value(), msg, data);
    }

    /**
     * 返回错误消息
     *
     * @return
     */
    public static ResponseVo error()
    {
        return ResponseVo.error("操作失败");
    }

    /**
     * 返回错误消息
     *
     * @param msg 返回内容
     * @return 警告消息
     */
    public static ResponseVo error(String msg)
    {
        return ResponseVo.error(msg, null);
    }

    /**
     * 返回错误消息
     *
     * @param msg 返回内容
     * @param data 数据对象
     * @return 警告消息
     */
    public static ResponseVo error(String msg, Object data)
    {
        return new ResponseVo(HttpStatus.INTERNAL_SERVER_ERROR.value(), msg, data);
    }

    /**
     * 返回错误消息
     *
     * @param code 状态码
     * @param msg 返回内容
     * @return 警告消息
     */
    public static ResponseVo error(int code, String msg)
    {
        return new ResponseVo(code, msg, null);
    }

}
