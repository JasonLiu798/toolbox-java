package com.atjl.kafka.core.thread;

import java.util.concurrent.TimeUnit;

/**
 * TimingStatisticsThread
 * 监控 统计用
 *
 * @since 1.0
 */
public class TimingBaseThread {

    protected int id;
    protected boolean start;
    protected int interval;
    protected int delay;
    protected TimeUnit timeUnit;

    public TimeUnit getTimeUnit() {
        return timeUnit;
    }

    public void setTimeUnit(TimeUnit timeUnit) {
        this.timeUnit = timeUnit;
    }


    public TimingBaseThread(int id, int interval) {
        this.id = id;
        this.interval = interval;
        this.delay = interval;
        this.timeUnit = TimeUnit.MILLISECONDS;
    }


    public TimingBaseThread(int id, int delay, int interval, TimeUnit timeUnit) {
        this.id = id;
        this.delay = delay;
        this.interval = interval;
        this.timeUnit = timeUnit;
    }

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isStart() {
        return start;
    }

    public void setStart(boolean start) {
        this.start = start;
    }

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }
}
