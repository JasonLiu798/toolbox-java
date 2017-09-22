package com.atjl.kafka.core.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * StatisticsThread
 * 监控 统计用
 *
 * @since 1.0
 */
public class BatchEventTimerThread extends BaseThread implements Runnable {
    private static final Logger LOG = LoggerFactory.getLogger(BatchEventTimerThread.class);

    @Override
    public void run() {
//        FetchDataMCThread.time2Process = true;
        if(LOG.isDebugEnabled()){
            LOG.debug("timer 2 process");
        }
    }
}
