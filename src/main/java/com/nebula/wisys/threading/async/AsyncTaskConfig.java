package com.nebula.wisys.threading.async;

import java.util.concurrent.Executor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync
public class AsyncTaskConfig implements AsyncConfigurer {

    final static Logger logger = LoggerFactory.getLogger(AsyncTaskConfig.class);

    @Value("${threading.async.thread.exec.core.pool}")
    private int threadPoolExecutorCorePoolSize;

    @Value("${threading.async.thread.exec.max.pool}")
    private int threadPoolExecutorMaxPoolSize;

    @Value("${threading.async.thread.exec.queue.cap}")
    private int threadPoolExecutorQueueCapacity;

    @Value("${threading.async.thread.exec.name.prefix}")
    private String threadPoolExecutorNamePrefix;
    
    @Bean(name = "asyncThreadPoolTaskExecutor")
    public Executor asyncThreadPoolTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(threadPoolExecutorCorePoolSize);
        executor.setMaxPoolSize(threadPoolExecutorMaxPoolSize);
        executor.setQueueCapacity(threadPoolExecutorQueueCapacity);
        executor.setThreadNamePrefix(threadPoolExecutorNamePrefix);
        executor.initialize();

        logger.info(String.format("Create a new pool task executor: core pool %d, max pool %d, queue cap %d, prefix %s",
            threadPoolExecutorCorePoolSize, threadPoolExecutorMaxPoolSize, threadPoolExecutorQueueCapacity, threadPoolExecutorNamePrefix));

        return executor;
    }

    @Override
    public Executor getAsyncExecutor() {
        return new SimpleAsyncTaskExecutor();
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return new AsyncTaskExceptionHandler();
    }
}
