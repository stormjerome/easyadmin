package com.zhxin.common.exception;

import com.zhxin.common.utils.StringUtil;

/**
 * @ClassName GlobalException
 * @Description //TODO
 * @Author singleZhang
 * @Email 405780096@qq.com
 * @Date 2020/10/21 0021 下午 3:39
 **/
public class GlobalException extends RuntimeException{

    /**
     * 所属模块
     */
    private String module;

    /**
     * 错误码
     */
    private int code;

    /**
     * 错误码对应的参数
     */
    private Object[] args;

    /**
     * 错误消息
     */
    private String message;

    public GlobalException(String module, int code, Object[] args, String message)
    {
        this.module = module;
        this.code = code;
        this.args = args;
        this.message = message;
    }

    public GlobalException(String module, int code, Object[] args)
    {
        this(module, code, args, null);
    }
    public GlobalException(String module, String message)
    {
        this(module, 200, null, message);
    }
    public GlobalException(int code, Object[] args)
    {
        this(null, code, args, null);
    }
    public GlobalException(String message, Integer code) { this(null, code, null, message); }
    public GlobalException(String message) { this(null, 200, null, message); }

    public String getModule()
    {
        return module;
    }
    public int getCode()
    {
        return code;
    }
    public Object[] getArgs()
    {
        return args;
    }
    public String getMessage()
    {
        return message;
    }
}
