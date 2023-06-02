package com.zhxin.core.execute.task;

import com.zhxin.common.utils.EasyUtil;
import com.zhxin.common.utils.ServletUtil;
import com.zhxin.common.utils.SpringUtil;
import com.zhxin.logic.system.model.SysAdminLoginLog;
import com.zhxin.logic.system.model.SysAdminOperateLog;
import com.zhxin.logic.system.model.SysAdminUser;
import com.zhxin.logic.system.service.ISysAdminLoginLogService;
import com.zhxin.logic.system.service.ISysAdminOperateLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.TimerTask;

/**
 * @ClassName LogTask
 * @Description //后台用户日志类任务
 * @Author singleZhang
 * @Email 405780096@qq.com
 * @Date 2020/11/20 0020 下午 5:26
 **/
public class LogTask {
   // private static final Logger sys_user_logger = LoggerFactory.getLogger("sys-admin-user");

    /**
     * 记录登陆信息
     */
    public static TimerTask addLoginLog(final SysAdminUser user ,final String message,
                                        final Object... args) {
        final String userAgent = ServletUtil.getRequest().getHeader("User-Agent");
        final String ip = EasyUtil.getIpAddr(ServletUtil.getRequest());
        return new TimerTask() {
            @Override
            public void run() {

                String address = EasyUtil.getRealAddressByIP(ip);
                StringBuilder s = new StringBuilder();
                s.append(EasyUtil.logBuffer(ip));
                s.append(address);
                s.append(EasyUtil.logBuffer(user.getUserName()));
                s.append(EasyUtil.logBuffer(message));

                // 打印信息到日志
                //sys_user_logger.info(s.toString(), args);

                // 封装对象
                SysAdminLoginLog logInfo = new SysAdminLoginLog();
                logInfo.setUserName(user.getUserName());
                logInfo.setLoginIp(ip);
                logInfo.setLoginLocation(address);
                logInfo.setUserAgent(userAgent);

                SysAdminUser updateUser = new SysAdminUser();
                updateUser.setAdminUserId(user.getAdminUserId());
                updateUser.setLoginIp(ip);
                updateUser.setLoginTime(new Date());
                logInfo.setUser(updateUser);
                SpringUtil.getBean(ISysAdminLoginLogService.class).insertLog(logInfo);

            }
        };
    }

    /**
     * 记录操作日志
     * */
    public static TimerTask addOperateLog(final SysAdminOperateLog sysAdminOperateLog){
        return  new TimerTask() {
            @Override
            public void run() {
                sysAdminOperateLog.setOperateLocation(EasyUtil.getRealAddressByIP(sysAdminOperateLog.getOperateIp()));
                SpringUtil.getBean(ISysAdminOperateLogService.class).insertLog(sysAdminOperateLog);
            }
        };
    }
}
