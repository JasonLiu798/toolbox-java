package com.atjl.dbtiming.domain;

import com.atjl.dbtiming.util.TimeCalcUtil;

import java.util.concurrent.TimeUnit;

public class IntervalTask extends BaseTimingTask {
    /**
     * delay
     */
    private long delay;

    /**
     * interval
     */
    private long interval;

    /**
     * is delayed
     */
    private boolean delayed;

    private TimeUnit timeUnit;

    @Override
    public long getNextExecuteTime() {
        if (delayed) {
            return TimeCalcUtil.nextExecuteTime(interval);
        } else {
            return TimeCalcUtil.nextExecuteTime(delay);
        }
    }


    public long getDelay() {
        return delay;
    }

    public void setDelay(long delay) {
        this.delay = delay;
    }

    public long getInterval() {
        return interval;
    }

    public TimeUnit getTimeUnit() {
        return timeUnit;
    }

    public void setTimeUnit(TimeUnit timeUnit) {
        this.timeUnit = timeUnit;
    }

    public void setInterval(long interval) {
        this.interval = interval;
    }

    public boolean isDelayed() {
        return delayed;
    }

    public void setDelayed(boolean delayed) {
        this.delayed = delayed;
    }
}
