package com.zhxin.core.execute;

import com.zhxin.common.utils.SpringUtil;
import com.zhxin.common.utils.ThreadUtil;

import java.util.TimerTask;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName AsyncExecutor
 * @Description //异步任务执行器
 * @Author singleZhang
 * @Email 405780096@qq.com
 * @Date 2020/11/20 0020 下午 5:18
 **/
public class AsyncExecutor {

    /**
     * 操作延迟10毫秒
     */
    private final int OPERATE_DELAY_TIME = 10;

    /**
     * 异步操作任务调度线程池
     */
    private ScheduledExecutorService executor = SpringUtil.getBean("scheduledExecutorService");


    /**
     * 单例
     */
    private AsyncExecutor(){}

    private static AsyncExecutor asyncExecutor = new AsyncExecutor();

    public static AsyncExecutor build() {
        return asyncExecutor;
    }

    /**
     * 执行任务
     *
     * @param task 任务
     */
    public void execute(TimerTask task) {
        executor.schedule(task, OPERATE_DELAY_TIME, TimeUnit.MILLISECONDS);
    }

    /**
     * 停止任务线程池
     */
    public void shutdown() {
        ThreadUtil.shutdownAndAwaitTermination(executor);
    }
}
