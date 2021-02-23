package com.example.demo.framework.common;

import com.example.demo.util.SpringHelper;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * 异步任务管理器
 * AsyncManager.me().execute(() -> {
    *                 执行方法 void();
    *             });
 * @author youfang
 */
public class AsyncManager {
    /**
     * 异步操作任务调度线程池
     */
    private final ThreadPoolTaskExecutor taskExecutor = SpringHelper.getBean("threadPoolTaskExecutor");

    /**
     * 单例模式
     */
    private AsyncManager() {
    }

    private static final AsyncManager ME = new AsyncManager();

    public static AsyncManager me() {
        return ME;
    }

    /**
     * 执行任务
     *
     * @param task 任务
     */
    public void execute(Runnable task){
        taskExecutor.execute(task);
    }

    /**
     * 停止线程池
     */
    public void shutdown() {
        taskExecutor.shutdown();
    }
}
