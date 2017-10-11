package com.atjl.util.thread.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class WaitTask implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(WaitTask.class);

    private CountDownLatch countDownLatch;
    private String finishMsg;
    private String errMsg;
    private Long waitMs;

    public WaitTask(CountDownLatch countDownLatch, String finishMsg, String errMsg) {
        this.countDownLatch = countDownLatch;
        this.finishMsg = finishMsg;
        this.errMsg = errMsg;
        this.waitMs = null;
    }

    public WaitTask(CountDownLatch countDownLatch, String finishMsg, String errMsg, Long waitMs) {
        this.countDownLatch = countDownLatch;
        this.finishMsg = finishMsg;
        this.errMsg = errMsg;
        this.waitMs = waitMs;
    }

    @Override
    public void run() {
        try {
            if (this.waitMs != null) {
                countDownLatch.await(waitMs, TimeUnit.MILLISECONDS);
            } else {
                countDownLatch.await();
            }
            logger.info(finishMsg);
        } catch (InterruptedException e) {
            logger.error(errMsg);
        }
    }
}
