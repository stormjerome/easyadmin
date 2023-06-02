package com.zhxin.common.annotation;

import com.zhxin.common.enums.OperateType;

import java.lang.annotation.*;

/**
 * 操作日志注解
 * */

@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {

    /**
     * 模块
     */
    String title() default "" ;

    /**
     * 功能
     */
    OperateType operateType() default OperateType.OTHER;

    /**
     * 是否保存请求的参数
     */
    boolean isSaveRequestData() default true;

}
