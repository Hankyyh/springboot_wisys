package com.nebula.wisys.scheduling.async;

import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

@Component
@Qualifier("asyncTaskImpl")
@EnableAsync
public class AsyncTaskImpl {

    final static Logger logger = LoggerFactory.getLogger(AsyncTaskImpl.class);

    @Async
    public void asyncMethodWithVoidReturnType() {
        logger.info("Async task (w/o return) - " + Thread.currentThread().getName());
    }

    @Async
    public CompletableFuture<Long> asyncMethodWithReturnType(long sleepMillisecond) {
        logger.info("Async task (with return) - " + Thread.currentThread().getName());
        try {
            Thread.sleep(sleepMillisecond);
        } catch (InterruptedException ex) {
            logger.error(String.format("%s (thread %s) - %s", ex.getClass().getName(), Thread.currentThread().getName(), ex.getLocalizedMessage()));
        }
        logger.info(String.format("Async task sleeps for %d ms", sleepMillisecond));
        return CompletableFuture.completedFuture(sleepMillisecond);
    }

    @Async("asyncThreadPoolTaskExecutor")
    public void asyncMethodWithConfiguredExecutor() {
        logger.info("Async task (asyncThreadPoolTaskExecutor) - " + Thread.currentThread().getName());
    }
}