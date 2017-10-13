package com.atjl.dbtiming.util;

import com.atjl.dbtiming.api.req.DynamicTaskParam;
import com.atjl.dbtiming.domain.BaseTimingTask;
import com.atjl.dbtiming.domain.CronTask;
import com.atjl.dbtiming.domain.IntervalTask;
import com.atjl.utilex.ApplicationContextHepler;

/**
 * 对象转换辅助类
 */
public class TaskTransferUtil {

    public static BaseTimingTask param2task(DynamicTaskParam param) {
        BaseTimingTask t = null;
        switch (param.getTaskConf().getTaskType()) {
            case CRON:
                CronTask ct = new CronTask();
                ct.setCronExpression(param.getTaskConf().getCronExpression());
                t = ct;
                break;
            case INTERVAL:
                IntervalTask it = new IntervalTask();
                it.setDelay(param.getTaskConf().getDelay());
                it.setInterval(param.getTaskConf().getInterval());
                it.setTimeUnit(param.getTaskConf().getTimeUnit());
                t = it;
                break;
        }
        if (t == null) {
            return null;
        }
        t.setTkey(param.getTkey());
        t.setServiceName(param.getTaskConf().getService());
        t.setServiceBean(ApplicationContextHepler.getBeanByName(t.getServiceName()));
        t.setTaskConf(param.getTaskConf());
        return t;
    }

    private TaskTransferUtil() {
        throw new UnsupportedOperationException();
    }
}
