package com.jason798.timing.task;

import com.jason798.timing.TimingContext;
import com.jason798.timing.entity.TaskEnum;

import java.util.concurrent.ScheduledFuture;

/**
 * task base class
 *
 * @author JasonLiu
 */
public abstract class BaseTask implements Runnable {
    /**
     * constructor
     *
     * @param tid
     */
    public BaseTask(String tid) {
        this.tid = tid;
    }

    /**
     * ################# basis ##################
     */
    /**
     * task unique id
     */
    protected String tid;
    /**
     * task type
     */
    protected TaskEnum type;
    /**
     * for cancel
     */
    protected ScheduledFuture future;

    /**
     * ################ timing #####################
     */
    /**
     * first delay time
     */
    protected long delayTime = 0;

    /**
     * ############### status #####################
     */
    /**
     * is running
     */
    protected volatile boolean running = false;
    /**
     * last start time
     */
    protected long lastStartTime = 0;
    /**
     * last stop time
     */
    protected long lastStopTime = 0;
    /**
     * submit to pool time
     */
    protected long submitTm = 0;


    /**
     * run before
     * for sys
     */
    public void before() {
        running = true;
        lastStartTime = System.currentTimeMillis();
    }

    /**
     * for runnable
     */
    abstract public void run();

    /**
     * run after
     */
    public void after() {
        lastStopTime = System.currentTimeMillis();
        running = false;
    }

    /**
     * remove status
     */
    public BaseTask removeStatus() {
        return TimingContext.removeTask(tid);
    }


    /**
     * ################### getter & setter ################################
     */
    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public TaskEnum getType() {
        return type;
    }

    public void setType(TaskEnum type) {
        this.type = type;
    }

    public long getDelayTime() {
        return delayTime;
    }

    public void setDelayTime(long delayTime) {
        this.delayTime = delayTime;
    }

    public long getLastStartTime() {
        return lastStartTime;
    }

    public void setLastStartTime(long lastStartTime) {
        this.lastStartTime = lastStartTime;
    }

    public long getLastStopTime() {
        return lastStopTime;
    }

    public void setLastStopTime(long lastStopTime) {
        this.lastStopTime = lastStopTime;
    }

    public ScheduledFuture getFuture() {
        return future;
    }

    public long getSubmitTm() {
        return submitTm;
    }

    public void setSubmitTm(long submitTm) {
        this.submitTm = submitTm;
    }

    public void setFuture(ScheduledFuture future) {
        this.future = future;
    }
}
