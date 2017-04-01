package com.jason798.timing.entity;

import java.io.Serializable;

/**
 * task status dto
 *
 * @author JasonLiu
 */
public class TaskStatusDto implements Serializable {
    private String taskId;
    private String type;

    private String lastStartTm;
    private String lastStopTm;

    private long startStopInterval;
    private Boolean runned;
    private boolean running;
    private long delay;
    private long interval;

    public TaskStatusDto(){
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLastStartTm() {
        return lastStartTm;
    }

    public void setLastStartTm(String lastStartTm) {
        this.lastStartTm = lastStartTm;
    }

    public String getLastStopTm() {
        return lastStopTm;
    }

    public void setLastStopTm(String lastStopTm) {
        this.lastStopTm = lastStopTm;
    }

    public long getStartStopInterval() {
        return startStopInterval;
    }

    public void setStartStopInterval(long startStopInterval) {
        this.startStopInterval = startStopInterval;
    }

    public Boolean isRunned() {
        return runned;
    }

    public void setRunned(Boolean runned) {
        this.runned = runned;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
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

    public void setInterval(long interval) {
        this.interval = interval;
    }
}
