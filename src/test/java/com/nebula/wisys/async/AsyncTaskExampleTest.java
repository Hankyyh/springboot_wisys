package com.nebula.wisys.async;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.junit.jupiter.api.Test;

import com.nebula.wisys.scheduling.async.*;

@ComponentScan("com.nebula.wisys.scheduling.async")
@ContextConfiguration(classes = { AsyncTaskConfig.class }, loader = AnnotationConfigContextLoader.class)
public class AsyncTaskExampleTest {

    final static Logger logger = LoggerFactory.getLogger(AsyncTaskExampleTest.class);

    @Autowired
    @Qualifier("asyncTaskImpl")
    private AsyncTaskImpl asyncTaskExample;

    @Test
    public void testAsyncAnnotationForMethodsWithVoidReturnType() {
        logger.info("Start - invoking an asynchronous method - " + Thread.currentThread().getName());
        asyncTaskExample.asyncMethodWithVoidReturnType();
        logger.info("End - invoking an asynchronous method");
    }

    @Test
    public void testAsyncAnnotationForMethodsWithReturnType() throws InterruptedException, ExecutionException {
        logger.info("Start - invoking an asynchronous method - " + Thread.currentThread().getName());
        final CompletableFuture<Long> future = asyncTaskExample.asyncMethodWithReturnType(5000);
        logger.info("End - invoking an asynchronous method");

        while (true) {
            if (future.isDone()) {
                logger.info("Result from asynchronous process - " + future.get());
                break;
            }
            logger.info("Continue doing something else. ");
            Thread.sleep(1000);
        }
    }

    @Test
    public void testAsyncAnnotationForMethodsWithConfiguredExecutor() {
        logger.info("Start - invoking an asynchronous method - " + Thread.currentThread().getName());
        asyncTaskExample.asyncMethodWithConfiguredExecutor();
        logger.info("End - invoking an asynchronous method");
    }

}
