package com.nebula.wisys.threading.scheduled;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTaskImpl {

    final static Logger logger = LoggerFactory.getLogger(ScheduledTaskImpl.class);

    static private long fixedDelayCnt = 0;
    static private long fixedRateCnt = 0;
    static private long cronCnt = 0;

    @Scheduled(fixedDelayString = "${scheduling.scheduled.delay.fixed:3000}",
        initialDelayString = "${scheduling.scheduled.delay.intial:60000}")
    public void scheduleFixedDelayTaskUsingExpression() {
        logger.info(String.format("Scheduled task: fixed delay count %d", fixedDelayCnt++));
    }

    @Scheduled(fixedRateString = "${scheduling.scheduled.rate.fixed:60000}")
    public void scheduleFixedRateTaskUsingExpression() {
        logger.info(String.format("Scheduled task: fixed rate count %d", fixedRateCnt++));
    }

    /*
    @Scheduled(cron = "${scheduling.scheduled.cron.expression:0 15 10 15 * ?}")
    public void scheduleTaskUsingExternalizedCronExpression() {
        logger.info(String.format("Scheduled task: cron %d", cronCnt++));
    }
    */
}
