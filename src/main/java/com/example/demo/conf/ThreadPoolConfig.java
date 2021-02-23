package com.example.demo.conf;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurerSupport;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.lang.reflect.Method;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;


/**
 * 线程池配置
 * spring通过 TaskExecutor来实现多线程并发编程。使用ThreadPoolExecutor可实现基于线程池的TaskExecutor
 *
 * 使用@EnableAsync开启对异步任务的支持，并通过在实际执行bean方法中使用@Async注解来声明一个异步任务
 *
 * @author youfang
 * @date 2020/09/23
 */
@Slf4j
@Configuration
@EnableAsync
public class ThreadPoolConfig extends AsyncConfigurerSupport {
    /** 核心线程池大小 */
    private static final int CORE_POOL_SIZE = 8;
    /** 最大可创建的线程数 */
    private static final int MAX_POOL_SIZE = 20;
    /** 队列最大长度 */
    private static final int QUEUE_CAPACITY = 10000;
    /** 线程池维护线程所允许的空闲时间（单位：秒） */
    private static final int KEEP_ALIVE_SECONDS = 300;

    /**
     * 线程池
     */
    @Bean(name = "threadPoolTaskExecutor")
    public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setThreadNamePrefix("spd-executor-");
        executor.setCorePoolSize(CORE_POOL_SIZE);
        executor.setMaxPoolSize(MAX_POOL_SIZE);
        executor.setQueueCapacity(QUEUE_CAPACITY);
        executor.setKeepAliveSeconds(KEEP_ALIVE_SECONDS);
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.setDaemon(true);
        return executor;
    }

    @Override
    public Executor getAsyncExecutor() {
        return threadPoolTaskExecutor();
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return (Throwable t, Method m, Object... args) -> {
            log.error("==========================" + t.getMessage() + "=======================", t);
            log.error("exception method:" + m.getName());
        };
    }
}
