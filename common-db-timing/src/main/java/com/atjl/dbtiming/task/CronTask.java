package com.atjl.dbtiming.task;

import com.atjl.dbtiming.domain.biz.TaskEnum;
import com.atjl.dbtiming.domain.biz.TimingConstant;
import com.atjl.dbtiming.helper.TimingInnerManager;
import com.atjl.dbtiming.util.DbTimingUtil;
import com.atjl.logdb.api.LogDbUtil;
import com.atjl.util.cron.CronExpression;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.text.ParseException;
import java.util.Date;

/**
 * cron execute task
 *
 * @author JasonLiu
 */
public class CronTask extends FixRateTask {
    private static Logger LOG = LoggerFactory.getLogger(CronTask.class);

    /**
     * cron expression
     */
    private CronExpression cronExpression;

    public CronTask(Long tid, TimingInnerManager helper, Object target, Method exe, String cronExpressionStr) throws ParseException {
        super(tid, helper, target, exe);
        this.type = TaskEnum.CRON;
        this.cronExpression = new CronExpression(cronExpressionStr);
    }

    /**
     * calc cron expression next run time
     * todo: if execute time too long
     *
     * @return
     */
    public Long cron2delay() {
        Date now = new Date();
        Date next = cronExpression.getNextValidTimeAfter(now);
        if (LOG.isDebugEnabled()) {
            LOG.debug("cron2delay now {},next {}", now, next);
        }

        return DbTimingUtil.getIntervalMs(now, next);
    }

    public void after(){
        super.after();
        reput();
    }

    /**
     * reput task to schedual pool
     */
    public void reput() {
        this.delayTime = cron2delay();
        LOG.debug("cron task next delay {}", this.delayTime);
        boolean res = innerManager.reSubmitCronTask(this);
        if (!res) {
            LogDbUtil.error(TimingConstant.MODULE_TIMING, "cron task reput res false,tid " + this.tid);
        }
    }


    /**
     * ############## getter & setter #################
     */
    public CronExpression getCronExpression() {
        return cronExpression;
    }

    public void setCronExpression(CronExpression cronExpression) {
        this.cronExpression = cronExpression;
    }
}
