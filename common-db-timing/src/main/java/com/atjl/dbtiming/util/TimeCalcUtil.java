package com.atjl.dbtiming.util;

import com.atjl.util.cron.CronExpression;

import java.text.ParseException;
import java.util.Date;

/**
 *
 */
public class TimeCalcUtil {
    private TimeCalcUtil() {
        throw new UnsupportedOperationException();
    }

    /**
     * cron表达式 下次执行时间
     *
     * @param cron
     * @return
     */
    public static long nextExecuteTime(String cron) {
        try {
            CronExpression cronExpression = new CronExpression(cron);
            Date next = cronExpression.getNextValidTimeAfter(new Date());
            return next.getTime();
        } catch (ParseException e) {
            //no possible,already checked before add
            return -1;
        }
    }

    /**
     * 计算下次执行时间
     *
     * @param interval
     * @return
     */
    public static long nextExecuteTime(long interval) {
        Date now = new Date();
        return now.getTime() + interval;
    }


}
