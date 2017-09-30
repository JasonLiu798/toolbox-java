package com.atjl.dbtiming.domain.biz;

import java.io.Serializable;

/**
 * task status dto
 *
 * @author JasonLiu
 */
public class TaskStatusDto implements Serializable {
    private Long tid;
    private String tkey;
    private String type;
    private String lastStartTm;
    private String lastStopTm;
    private String exeNextTm;
    private Long exeTime;
    private Boolean end = null;

    //runned counter
    private Long runnedCounter;
    //max run times
    private Long maxRunCount;
    // is now running
    private boolean running;
    //delay
    private String delay;
    // interval
    private String interval;
    // cond status
    private Boolean cond;
    // submit to pool time
    private String submitTm;

    public TaskStatusDto() {
    }

    public Long getMaxRunCount() {
        return maxRunCount;
    }

    public void setMaxRunCount(Long maxRunCount) {
        this.maxRunCount = maxRunCount;
    }

    public String getExeNextTm() {
        return exeNextTm;
    }

    public void setExeNextTm(String exeNextTm) {
        this.exeNextTm = exeNextTm;
    }

    public Boolean getCond() {
        return cond;
    }

    public void setCond(Boolean cond) {
        this.cond = cond;
    }

    public Boolean getEnd() {
        return end;
    }

    public Long getRunnedCounter() {
        return runnedCounter;
    }

    public void setRunnedCounter(Long runnedCounter) {
        this.runnedCounter = runnedCounter;
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

    public Long getExeTime() {
        return exeTime;
    }

    public void setExeTime(Long exeTime) {
        this.exeTime = exeTime;
    }

    public Long getTid() {
        return tid;
    }

    public void setTid(Long tid) {
        this.tid = tid;
    }

    public String getTkey() {
        return tkey;
    }

    public void setTkey(String tkey) {
        this.tkey = tkey;
    }

    public Boolean isEnd() {
        return end;
    }

    public void setEnd(Boolean end) {
        this.end = end;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public String getDelay() {
        return delay;
    }

    public void setDelay(String delay) {
        this.delay = delay;
    }

    public String getInterval() {
        return interval;
    }

    public void setInterval(String interval) {
        this.interval = interval;
    }

    public String getSubmitTm() {
        return submitTm;
    }

    public void setSubmitTm(String submitTm) {
        this.submitTm = submitTm;
    }
}
