package com.atjl.retry.api.option;


public class InstanceRetryConf {

    /**
     * 立即重试次数
     */
    private int retryCount = 1;
    /**
     * 立即重试 等待间隔
     */
    private long waitMs = 0;

    public int getRetryCount() {
        return retryCount;
    }

    public void setRetryCount(int retryCount) {
        this.retryCount = retryCount;
    }

    public long getWaitMs() {
        return waitMs;
    }

    public void setWaitMs(long waitMs) {
        this.waitMs = waitMs;
    }
}
