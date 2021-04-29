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

    @Scheduled(fixedDelayString = "${threading.scheduled.delay.fixed}",
        initialDelayString = "${threading.scheduled.delay.initial}")
    public void scheduleFixedDelayTaskUsingExpression() {
        logger.info(String.format("Scheduled task: fixed delay count %d", fixedDelayCnt++));
    }

    @Scheduled(fixedRateString = "${threading.scheduled.rate.fixed}")
    public void scheduleFixedRateTaskUsingExpression() {
        logger.info(String.format("Scheduled task: fixed rate count %d", fixedRateCnt++));
    }

    /*
    @Scheduled(cron = "${threading.scheduled.cron.expression}")
    public void scheduleTaskUsingExternalizedCronExpression() {
        logger.info(String.format("Scheduled task: cron %d", cronCnt++));
    }
    */
}
