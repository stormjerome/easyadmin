package com.zhxin.core.aspect;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhxin.common.annotation.Log;
import com.zhxin.common.utils.EasyUtil;
import com.zhxin.common.utils.ServletUtil;
import com.zhxin.core.execute.AsyncExecutor;
import com.zhxin.core.execute.task.LogTask;
import com.zhxin.core.security.LoginUser;
import com.zhxin.core.security.util.UserUtil;
import com.zhxin.logic.system.model.SysAdminOperateLog;
import com.zhxin.logic.system.model.SysAdminUser;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * @ClassName LogAspect
 * @Description //操作日志处理
 * @Author singleZhang
 * @Email 405780096@qq.com
 * @Date 2020/11/24 0024 下午 2:48
 **/
@Aspect
@Component
public class LogAspect {

    /**
     * 配置织入点
     */
    @Pointcut("@annotation(com.zhxin.common.annotation.Log)")
    public void logPointCut() {

    }

    /**
     * 处理完请求后执行
     *
     * @param joinPoint 切点
     */
    @AfterReturning(pointcut = "logPointCut()")
    public void doAfterReturning(JoinPoint joinPoint) {
        saveLog(joinPoint, null);
    }

    /**
     * 拦截异常操作
     *
     * @param joinPoint 切点
     * @param e 异常
     */
    @AfterThrowing(value = "logPointCut()" , throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Exception e) {
        saveLog(joinPoint, e);
    }

    /**
     * 保存日志
     * */
    private void saveLog(final JoinPoint joinPoint, final Exception e){
        try {
            // 获得注解
            Log controllerLog = getAnnotationLog(joinPoint);
            if (controllerLog == null) return;

            // 获取当前的用户
            LoginUser loginUser = UserUtil.getLoginUser();
            SysAdminUser user = loginUser.getUser();

            SysAdminOperateLog operateLog = new SysAdminOperateLog();

            operateLog.setUserId(user.getAdminUserId());
            operateLog.setUserName(user.getUserName());
            operateLog.setCreatedBy(user.getAdminUserId());
            operateLog.setUpdatedBy(user.getAdminUserId());

            // 请求的地址
            String ip = EasyUtil.getIpAddr(ServletUtil.getRequest());
            operateLog.setOperateIp(ip);
            operateLog.setRequestUrl(ServletUtil.getRequest().getRequestURI());
            operateLog.setRequestType(ServletUtil.getRequest().getMethod());


            // 设置方法名称
            String className = joinPoint.getTarget().getClass().getName();
            String methodName = joinPoint.getSignature().getName();
            operateLog.setMethod(className + "." + methodName + "()");
            // 处理设置注解上的参数
            getControllerMethodDescription(controllerLog, operateLog);
            // 任务:记录日志
            AsyncExecutor.build().execute(LogTask.addOperateLog(operateLog));
        } catch (Exception ext) {
            // 记录本地异常日志
            System.out.println(ext.getMessage());
        }
    }

    /**
     * 获取注解中对方法的描述信息 用于Controller层注解
     *
     */
    private void getControllerMethodDescription(Log log, SysAdminOperateLog operateLog) throws Exception {
        // 设置action动作
        operateLog.setOperateType(log.operateType().ordinal());
        // 设置标题
        operateLog.setTitle(log.title());

        // 是否需要保存request，参数和值
        if (log.isSaveRequestData()) {
            // 获取参数的信息，传入到数据库中。
            setRequestValue(operateLog);
        }
    }

    /**
     * 获取请求的参数，放到log中
     *
     */
    private void setRequestValue(SysAdminOperateLog operateLog){
        Map<String, String[]> map = ServletUtil.getRequest().getParameterMap();
        String params = toJSon(map);
        operateLog.setRequestParam(params);
    }

    private static String toJSon(Object object) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            return objectMapper.writeValueAsString(object);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


        /**
         * 是否存在注解，如果存在就获取
         */
    private Log getAnnotationLog(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();

        if (method != null) {
            return method.getAnnotation(Log.class);
        }
        return null;
    }
}
