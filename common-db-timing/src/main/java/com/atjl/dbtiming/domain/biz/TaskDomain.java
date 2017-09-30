package com.atjl.dbtiming.domain.biz;

import java.io.Serializable;

/**
 * task domain
 */
public class TaskDomain implements Serializable {
    private Long tid;

    private String tkey;

    private String tservice;

    private String param;

    private String confType;

    private String confCronExpression;

    private Long confDelayTm;

    private Long confIntervalTm;

    private Long confExeTimes;

    private String processor;

    private String tmutex;

    private String confHasParam;

    private Long mutexTm;

    private String tstatus;

    private Long aliveTm;

    private String valid;

    private Long runCnt;

    private Long lastStartMs;

    private Long lastStopMs;

    private Long crtTm;


    private static final long serialVersionUID = 1L;

    public TaskDomain(){}

    public String getConfHasParam() {
        return confHasParam;
    }

    public void setConfHasParam(String confHasParam) {
        this.confHasParam = confHasParam;
    }

    public Long getTid() {
        return tid;
    }

    public void setTid(Long tid) {
        this.tid = tid;
    }

    public Long getCrtTm() {
        return crtTm;
    }

    public void setCrtTm(Long crtTm) {
        this.crtTm = crtTm;
    }

    public String getTkey() {
        return tkey;
    }

    public void setTkey(String tkey) {
        this.tkey = tkey == null ? null : tkey.trim();
    }

    public String getTservice() {
        return tservice;
    }

    public void setTservice(String tservice) {
        this.tservice = tservice == null ? null : tservice.trim();
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param == null ? null : param.trim();
    }

    public String getConfType() {
        return confType;
    }

    public void setConfType(String confType) {
        this.confType = confType == null ? null : confType.trim();
    }

    public String getConfCronExpression() {
        return confCronExpression;
    }

    public void setConfCronExpression(String confCronExpression) {
        this.confCronExpression = confCronExpression == null ? null : confCronExpression.trim();
    }

    public Long getConfDelayTm() {
        return confDelayTm;
    }

    public void setConfDelayTm(Long confDelayTm) {
        this.confDelayTm = confDelayTm;
    }

    public Long getConfIntervalTm() {
        return confIntervalTm;
    }

    public void setConfIntervalTm(Long confIntervalTm) {
        this.confIntervalTm = confIntervalTm;
    }

    public Long getConfExeTimes() {
        return confExeTimes;
    }

    public void setConfExeTimes(Long confExeTimes) {
        this.confExeTimes = confExeTimes;
    }

    public String getProcessor() {
        return processor;
    }

    public void setProcessor(String processor) {
        this.processor = processor == null ? null : processor.trim();
    }

    public String getTmutex() {
        return tmutex;
    }

    public void setTmutex(String tmutex) {
        this.tmutex = tmutex == null ? null : tmutex.trim();
    }

    public Long getMutexTm() {
        return mutexTm;
    }

    public void setMutexTm(Long mutexTm) {
        this.mutexTm = mutexTm;
    }

    public String getTstatus() {
        return tstatus;
    }

    public Long getRunCnt() {
        return runCnt;
    }

    public void setRunCnt(Long runCnt) {
        this.runCnt = runCnt;
    }

    public Long getLastStartMs() {
        return lastStartMs;
    }

    public void setLastStartMs(Long lastStartMs) {
        this.lastStartMs = lastStartMs;
    }

    public Long getLastStopMs() {
        return lastStopMs;
    }

    public void setLastStopMs(Long lastStopMs) {
        this.lastStopMs = lastStopMs;
    }


    public void setTstatus(String tstatus) {
        this.tstatus = tstatus == null ? null : tstatus.trim();
    }

    public Long getAliveTm() {
        return aliveTm;
    }

    public void setAliveTm(Long aliveTm) {
        this.aliveTm = aliveTm;
    }

    public String getValid() {
        return valid;
    }

    public void setValid(String valid) {
        this.valid = valid == null ? null : valid.trim();
    }
}