package com.atjl.dbtiming.util;


import com.atjl.dbtiming.api.domain.TaskType;
import com.atjl.dbtiming.api.req.DynamicTaskParam;
import com.atjl.dbtiming.api.req.TaskConf;

import java.util.concurrent.TimeUnit;

public class TaskParamUtil {
    private TaskParamUtil() {
        throw new UnsupportedOperationException();
    }

    /**
     * 生成普通cron表达式 任务
     * 永久执行
     *
     * @param key
     * @param cron
     * @param service
     */
    public static DynamicTaskParam genNewCronTask(String key, String service, String cron) {
        DynamicTaskParam p = new DynamicTaskParam();
        p.setTkey(key);
        p.setTaskConf(getCronTaskConf(cron, service));
        return p;
    }

    public static DynamicTaskParam genFixRateLimitCntTask(String key, String service, long delay, long interval, TimeUnit tu, int maxCnt) {
        DynamicTaskParam p = new DynamicTaskParam();
        p.setTkey(key);
        TaskConf tc = new TaskConf();
        tc.setDelay(delay);
        tc.setService(service);
        tc.setInterval(interval);
        tc.setTimeUnit(tu);
        tc.setMaxRunCnt(maxCnt);
        tc.setHasCond(false);
        tc.setHasParam(false);
        tc.setTaskType(TaskType.INTERVAL);
        p.setTaskConf(tc);
        return p;
    }

    /**
     * 生成 普通 cron表达式配置
     */
    public static TaskConf getCronTaskConf(String cron, String service) {
        TaskConf tc = new TaskConf();
        tc.setCronExpression(cron);
        tc.setHasCond(false);
        tc.setHasParam(false);
        tc.setService(service);
        tc.setMaxRunCnt(0);//永久
        tc.setTaskType(TaskType.CRON);
        return tc;
    }

    /**
     * @param param
     * @return
     */
    public static int param2option(DynamicTaskParam param) {

        return 0;
    }
}
