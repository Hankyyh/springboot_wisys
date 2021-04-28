package com.nebula.wisys.threading.async;

import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;

public class AsyncTaskExceptionHandler implements AsyncUncaughtExceptionHandler {

    final static Logger logger = LoggerFactory.getLogger(AsyncTaskExceptionHandler.class);
    
    // This method can only catch exception from void-return async method
    @Override
    public void handleUncaughtException(final Throwable throwable, final Method method, final Object... obj) {
        logger.error("Exception - " + throwable.getMessage());
        logger.error("Method - " + method.getName());
        for (final Object param : obj) {
            logger.error("Param - " + param);
        }
    }
}
