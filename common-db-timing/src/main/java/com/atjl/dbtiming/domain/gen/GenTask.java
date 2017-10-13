package com.atjl.dbtiming.domain.gen;

import java.io.Serializable;

public class GenTask implements Serializable {
    private Long tid;

    private String tkey;

    private String taskType;

    private String datas;

    private String tmutex;

    private Long mutexTm;

    private String lastExecutor;

    private Long lastExecuteTm;

    private String tstatus;

    private String valid;

    private Long crtTm;

    private static final long serialVersionUID = 1L;

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
        this.tkey = tkey == null ? null : tkey.trim();
    }

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType == null ? null : taskType.trim();
    }

    public String getDatas() {
        return datas;
    }

    public void setDatas(String datas) {
        this.datas = datas == null ? null : datas.trim();
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

    public String getLastExecutor() {
        return lastExecutor;
    }

    public void setLastExecutor(String lastExecutor) {
        this.lastExecutor = lastExecutor == null ? null : lastExecutor.trim();
    }

    public Long getLastExecuteTm() {
        return lastExecuteTm;
    }

    public void setLastExecuteTm(Long lastExecuteTm) {
        this.lastExecuteTm = lastExecuteTm;
    }

    public String getTstatus() {
        return tstatus;
    }

    public void setTstatus(String tstatus) {
        this.tstatus = tstatus == null ? null : tstatus.trim();
    }

    public String getValid() {
        return valid;
    }

    public void setValid(String valid) {
        this.valid = valid == null ? null : valid.trim();
    }

    public Long getCrtTm() {
        return crtTm;
    }

    public void setCrtTm(Long crtTm) {
        this.crtTm = crtTm;
    }
}