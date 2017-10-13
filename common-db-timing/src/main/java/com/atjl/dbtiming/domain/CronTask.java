package com.atjl.dbtiming.domain;


import com.atjl.dbtiming.util.TimeCalcUtil;

public class CronTask extends BaseTimingTask {
    /**
     *
     */
    private String cronExpression;

    public String getCronExpression() {
        return cronExpression;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }

    @Override
    public long getNextExecuteTime() {
        return TimeCalcUtil.nextExecuteTime(cronExpression);
    }

}
