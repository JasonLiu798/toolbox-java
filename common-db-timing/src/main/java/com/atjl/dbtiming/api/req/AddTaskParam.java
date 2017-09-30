package com.atjl.dbtiming.api.req;

import com.atjl.dbtiming.api.RetCode;
import com.atjl.dbtiming.domain.biz.TimingConstant;
import com.atjl.util.common.DateUtil;

import java.io.Serializable;

/**
 * add task param
 */
public class AddTaskParam implements Serializable {
    // tid
    protected long tid;
    // key
    protected String tkey;
    // service
    protected String service;
    // options
    protected boolean newTask = false;

    // has param
    protected boolean hasParam = false;
    // has cond
    protected boolean hasCond = false;


    // run forever
    protected boolean forever = false;
    //mutex
    protected boolean mutex = false;
    // task type
    protected String type;
    //add a task,check is unique
    protected boolean checkUnique = false;
    //unique check time interval
    protected Long uniqueChkInterval = TimingConstant.UNIQUE_CHECK_INTERVAL;

    // delay
    protected Long delay;
    //interval
    protected Long interval;


    // has cnt
    protected boolean hasCnt = false;
    // max time
    protected Long maxTime;


    //parameter
    protected String param;
    //mutex tm
    protected Long mutexTm = 0L;
    //status
    protected String status;


    // is cron
    protected boolean cron = false;
    //cron
    protected String cronExpression;


    protected boolean force = false;

    protected RetCode failReason = RetCode.SUCCESS;

    protected boolean persist;
    //crt tm
    protected Long crtTm;

    public boolean isSucc() {
        if (failReason == RetCode.SUCCESS) {
            return true;
        }
        return false;
    }

    public boolean isFail() {
        return !isSucc();
    }

    /**
     * build FixRateCntCondTaskParam
     *
     * @param service
     * @param paramStr
     * @param maxTime
     * @param delay
     * @param interval
     * @return
     */
    public static AddTaskParam buildNewFixRateCntConditionParam(String service, String paramStr, Long maxTime, Long delay, Long interval) {
        AddTaskParam param = buildNewFixRateCntParam(service, paramStr, maxTime, delay, interval);
        param.enableCond().setType(TimingConstant.TP_DYN_FIXRATE_CNT_COND);
        return param;
    }

    /**
     * build FixRateCntTaskParam
     *
     * @param service
     * @param paramStr
     * @param maxTime
     * @param delay
     * @param interval
     * @return
     */
    public static AddTaskParam buildNewFixRateCntParam(
            String service,
            String paramStr,
            Long maxTime,
            Long delay, Long interval) {
        AddTaskParam param = new AddTaskParam();
        param.setService(service)
                .enableLimit(maxTime)
                .setDelay(delay)
                .setInterval(interval)
                .enableParam(paramStr)
                .disableCond()
                .enableNew()
                .setTkey(service + "-" + paramStr)
                .enableUniqueChk()
                .setType(TimingConstant.TP_DYN_FIXRATE_CNT)
                .setCrtTmNow();
        return param;
    }

    /**
     * build CronTask
     *
     * @return public AddTaskParam buildNewCronParam(String key, String service, String cronExpression) {
     * AddTaskParam param = new AddTaskParam();
     * param.setService(service);
     * param.setTkey(key);
     * param.setCron(true);
     * param.setCronExpression(cronExpression);
     * param.setStatus(TimingConstant.STATUS_FREE);
     * param.setAliveTm(DateUtil.getNowTS());
     * return param;
     * }
     */

    public AddTaskParam() {
    }




    public boolean isCheckUnique() {
        return checkUnique;
    }

    public long getUniqueChkInterval() {
        return uniqueChkInterval;
    }

    public void setUniqueChkInterval(Long uniqueChkInterval) {
        this.uniqueChkInterval = uniqueChkInterval;
    }

    public void setCheckUnique(boolean checkUnique) {
        this.checkUnique = checkUnique;
    }

    public AddTaskParam enableUniqueChk() {
        this.checkUnique = true;
        return this;
    }

    public RetCode getFailReason() {
        return failReason;
    }

    public void setFailReason(RetCode failReason) {
        this.failReason = failReason;
    }


    public Long getCrtTm() {
        return crtTm;
    }

    public AddTaskParam setCrtTmNow() {
        this.crtTm = DateUtil.getNowTS();
        return this;
    }

    public AddTaskParam setCrtTm(Long crtTm) {
        this.crtTm = crtTm;
        return this;
    }

    public boolean isCron() {
        return cron;
    }

    public AddTaskParam setCron(boolean cron) {
        this.cron = cron;
        return this;
    }

    public AddTaskParam enableCron() {
        this.cron = true;
        return this;
    }

    public AddTaskParam enableNew() {
        this.newTask = true;
        this.status = TimingConstant.STATUS_FREE;
        return this;
    }


    public boolean isForce() {
        return force;
    }

    public boolean isNewTask() {
        return newTask;
    }

    public void setNewTask(boolean newTask) {
        this.newTask = newTask;
    }

    public void setForce(boolean force) {
        this.force = force;
    }

    public String getCronExpression() {
        return cronExpression;
    }

    public AddTaskParam setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
        return this;
    }

    public boolean isMutex() {
        return mutex;
    }

    public String getStatus() {
        return status;
    }

    public AddTaskParam setStatus(String status) {
        this.status = status;
        return this;
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

    public AddTaskParam setTkey(String tkey) {
        this.tkey = tkey;
        return this;
    }

    public void setDelay(long delay) {
        this.delay = delay;
    }

    public AddTaskParam setInterval(long interval) {
        this.interval = interval;
        return this;
    }

    public AddTaskParam setMaxTime(long maxTime) {
        this.maxTime = maxTime;
        return this;
    }

    public boolean getMutex() {
        return mutex;
    }

    public void setMutex(boolean mutex) {
        this.mutex = mutex;
    }

    public long getMutexTm() {
        return mutexTm;
    }

    public void setMutexTm(long mutexTm) {
        this.mutexTm = mutexTm;
    }

    public boolean isForever() {
        return forever;
    }

    public boolean isHasParam() {
        return hasParam;
    }

    public AddTaskParam setHasParam(boolean hasParam) {
        this.hasParam = hasParam;
        return this;
    }

    public AddTaskParam enableParam(String param) {
        this.hasParam = true;
        this.param = param;
        return this;
    }

    public String getParam() {
        return param;
    }

    public String getType() {
        return type;
    }

    public AddTaskParam setType(String type) {
        this.type = type;
        return this;
    }

    public AddTaskParam setParam(String param) {
        this.param = param;
        return this;
    }

    public void setForever(boolean forever) {
        this.forever = forever;
    }

    public AddTaskParam enableForever() {
        this.forever = true;
        this.hasCnt = false;
        return this;
    }

    public Long getDelay() {
        return delay;
    }

    public AddTaskParam setDelay(Long delay) {
        this.delay = delay;
        return this;
    }

    public Long getInterval() {
        return interval;
    }

    public AddTaskParam setInterval(Long interval) {
        this.interval = interval;
        return this;
    }

    public Long getMaxTime() {
        return maxTime;
    }


    public String getService() {
        return service;
    }

    public AddTaskParam setService(String service) {
        this.service = service;
        return this;
    }

    public boolean isHasCond() {
        return hasCond;
    }

    public void setHasCond(boolean hasCond) {
        this.hasCond = hasCond;
    }

    public AddTaskParam enableCond() {
        this.hasCond = true;
        return this;
    }

    public AddTaskParam disableCond() {
        this.hasCond = false;
        return this;
    }

    public boolean isPersist() {
        return persist;
    }

    public AddTaskParam enablePersist() {
        this.persist = true;
        return this;
    }

    public void setPersist(boolean persist) {
        this.persist = persist;
    }

    public boolean isHasCnt() {
        return hasCnt;
    }

    public void setHasCnt(boolean hasCnt) {
        this.hasCnt = hasCnt;
    }

    public AddTaskParam enableLimit(Long cnt) {
        this.hasCnt = true;
        this.maxTime = cnt;
        return this;
    }
}
