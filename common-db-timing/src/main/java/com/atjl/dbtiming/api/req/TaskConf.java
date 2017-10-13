package com.atjl.dbtiming.api.req;


import com.atjl.dbtiming.api.domain.TaskType;

import java.util.concurrent.TimeUnit;

/**
 * 任务配置
 */
public class TaskConf {
    /**
     * 任务类型
     */
    private TaskType taskType = TaskType.CRON;

    /**
     * 业务 spring bean name，service 需要实现 ITaskExecute
     */
    protected String service;

    /**
     * ################## 条件 必填项 ##################
     */
    /**
     * cron表达式，type=CRON,必填
     */
    private String cronExpression;

    /**
     * delay和interval的时间单位
     */
    private TimeUnit timeUnit = TimeUnit.MILLISECONDS;
    /**
     * 延迟时间 ms
     */
    private Long delay = 0L;
    /**
     * 执行间隔
     */
    private Long interval;

    /**
     * ############# 执行前可选项 ######################
     */
    /**
     * 是否有参数 ，service 需要实现 ITaskParam
     */
    private boolean hasParam = false;
    /**
     * 执行参数，hasParam=true，必填
     */
    private String param;

    /**
     * ################# 提前终止选项 ################
     */
    /**
     * 执行完毕后判定条件是否达成，true则不在放入 调度队列 , service 需要实现 ICond
     */
    private boolean hasCond = false;
    /**
     * 执行次数
     * 0 永久执行
     * x 执行指定次数
     */
    private long maxRunCnt = 0;
    /**
     * 已经执行次数
     */
    private long hasRunCnt = 0;

    public String getCronExpression() {
        return cronExpression;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }

    public TimeUnit getTimeUnit() {
        return timeUnit;
    }

    public void setTimeUnit(TimeUnit timeUnit) {
        this.timeUnit = timeUnit;
    }

    public Long getDelay() {
        return delay;
    }

    public void setDelay(Long delay) {
        this.delay = delay;
    }

    public Long getInterval() {
        return interval;
    }

    public void setInterval(Long interval) {
        this.interval = interval;
    }

    public boolean isHasParam() {
        return hasParam;
    }

    public void setHasParam(boolean hasParam) {
        this.hasParam = hasParam;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public boolean isHasCond() {
        return hasCond;
    }

    public void setHasCond(boolean hasCond) {
        this.hasCond = hasCond;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public long getMaxRunCnt() {
        return maxRunCnt;
    }

    public void setMaxRunCnt(long maxRunCnt) {
        this.maxRunCnt = maxRunCnt;
    }

    public long getHasRunCnt() {
        return hasRunCnt;
    }

    public TaskType getTaskType() {
        return taskType;
    }

    public void setTaskType(TaskType taskType) {
        this.taskType = taskType;
    }

    public void setHasRunCnt(long hasRunCnt) {
        this.hasRunCnt = hasRunCnt;
    }
}
