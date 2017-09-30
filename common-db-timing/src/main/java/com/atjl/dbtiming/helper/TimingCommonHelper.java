package com.atjl.dbtiming.helper;

import com.atjl.dbtiming.api.*;
import com.atjl.dbtiming.api.req.AddTaskParam;
import com.atjl.dbtiming.api.req.RexeTaskParam;
import com.atjl.dbtiming.domain.biz.TaskDomain;
import com.atjl.logdb.api.LogDbUtil;
import com.atjl.util.character.StringCheckUtil;
import com.atjl.util.common.DateUtil;
import com.atjl.util.cron.CronExpression;
import com.atjl.util.reflect.ReflectClassUtil;
import com.atjl.util.reflect.ReflectMethodUtil;
import com.atjl.utilex.ApplicationContextHepler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import com.atjl.dbtiming.domain.biz.TimingConstant;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.text.ParseException;

/**
 * common functions
 */
@Component
public class TimingCommonHelper {
    private static Logger LOG = LoggerFactory.getLogger(TimingCommonHelper.class);
    @Resource
    private TimingLockHelper timingLockHelper;

    /**
     * valid cron expression
     *
     * @param cronExpression
     * @return
     */
    public boolean validCronExpression(String cronExpression) {
        try {
            new CronExpression(cronExpression);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

    /**
     * stop or invalid
     *
     * @param t
     * @return
     */
    public boolean isStopOrInvalid(TaskDomain t) {
        String valid = t.getValid();
        String status = t.getTstatus();
        if (StringCheckUtil.isEmpty(valid)
                || StringCheckUtil.equal(valid, TimingConstant.N)
                || StringCheckUtil.isEmpty(status)
                || StringCheckUtil.equal(status, TimingConstant.STATUS_END)
                ) {
            return true;
        }
        return false;
    }

    /**
     * db task transform to param
     *
     * @param t
     * @return
     */
    public RexeTaskParam db2param(TaskDomain t) {
        if (t == null) {
            return RexeTaskParam.buildFailNull();
        }
        if (isStopOrInvalid(t)) {
            LogDbUtil.error(TimingConstant.MODULE_TIMING, "check task,task stop or invalid");
            return RexeTaskParam.buildFailStopOrInvalid();
        }
        RexeTaskParam p = new RexeTaskParam();
        TimingConstant.validTp(t.getConfType());
        switch (t.getConfType()) {
            case TimingConstant.TP_CRON:
                p.enableCron().setCronExpression(t.getConfCronExpression());
                break;
            case TimingConstant.TP_DYN_FIXRATE_CNT_COND:
                p.enableCond();
                dynFixRateCnt(t,p);
                break;
            case TimingConstant.TP_DYN_FIXRATE_CNT:
                dynFixRateCnt(t,p);
                break;
            default:
                LogDbUtil.error(TimingConstant.MODULE_TIMING, "task type unsupport,actual conf type:" + t.getConfType());
                return null;
        }
        p.setStatus(t.getTstatus()).enablePersist().setTkey(t.getTkey()).setService(t.getTservice());
        p.enableDbNew(t.getRunCnt(), t.getAliveTm()).setCrtTm(t.getCrtTm());
        p.setLastStopMs(t.getLastStopMs());
        p.setLastStartMs(t.getLastStartMs());
        return p;
    }


    private void dynFixRateCnt(TaskDomain t ,RexeTaskParam p ){
        if (StringCheckUtil.equal(t.getConfHasParam(), TimingConstant.Y)) {
            p.enableParam(t.getParam());
        }
        p.enableLimit(t.getConfExeTimes())
                .setInterval(t.getConfIntervalTm() == null ? 0L : t.getConfIntervalTm())
                .setDelay(t.getConfDelayTm() == null ? 0L : t.getConfDelayTm());
    }

    /**
     * check task can process
     *
     * @param t
     * @return
     */
    public RetCode checkTaskCanProcess(TaskDomain t) {
        if (t == null) {
            LogDbUtil.error(TimingConstant.MODULE_TIMING, "gen task null");
            return RetCode.DB_TASK_NULL;
        }
        RexeTaskParam p = db2param(t);
        if (p.isFail()) {
            LogDbUtil.error(TimingConstant.MODULE_TIMING, "trans form db task to param null");
            return p.getFailReason();
        }
        return checkTaskCanProcess(p);
    }


    /**
     * check task can process
     * and format parameter not null
     *
     * @param param
     * @return
     */
    public RetCode checkTaskCanProcess(AddTaskParam param) {
        if (param == null) {
            return RetCode.TASK_PARAM_NULL;
        }
        boolean chkDb = false;
        RexeTaskParam rparam = null;
        if (param instanceof RexeTaskParam) {
            rparam = (RexeTaskParam) param;
            chkDb = true;
        }

        //check service exist
        String beanName = param.getService();
        Object target = null;
        try {
            target = ApplicationContextHepler.getBeanByName(beanName);
            if (target == null) {
                LogDbUtil.error(TimingConstant.MODULE_TIMING, "task service clz " + beanName + " not exist");
                return RetCode.BEAN_NOT_EXIST;
            }
        } catch (Exception e) {
            LogDbUtil.error(TimingConstant.MODULE_TIMING, "task service clz " + beanName + " not exist", e);
            return RetCode.GET_BEAN_FAIL;
        }

        if (param.isCron()) {
            //check cron
            if (!validCronExpression(param.getCronExpression())) {
                return RetCode.CRON_EXPESSION_INVALID;
            }
        } else {
            //check delay
            if (param.getDelay() == null) {
                //param.setDelay(0L);
                return RetCode.DELAY_NULL;
            }
            if (param.getDelay() <= 0) {
                return RetCode.DELAY_BELOW_ZERO;
            }
            //set delay to min delay
            if (param.getDelay() < TimingConstant.DFT_MONITOR_DELAY) {
                param.setDelay(TimingConstant.DFT_MONITOR_DELAY);
            }

            //check interval
            if (param.getInterval() == null) {
                //param.setInterval(0);
                return RetCode.INTERVAL_NULL;
            }
            if (param.getInterval() <= 0L) {
                return RetCode.INTERVAL_BELOW_ZERO;
            }
            //if interval too small,set interval to 1s
            if (param.getInterval() < TimingConstant.MIN_INTERVAL) {
                param.setInterval(TimingConstant.MIN_INTERVAL);
            }

            //if cond,check implement ICond
            if (param.isHasCond()) {
                if (!param.isHasParam()) {
                    //if use cond,must has param
                    return RetCode.HAS_COND_BUT_NOT_HAS_PARAM;
                }
                if (!ReflectClassUtil.chkAImplementB(target, ICond.class)) {
                    return RetCode.HAS_COND_NOT_IMPLEMENT_INF;
                }
            }

            //check run count reach max cnt or not
            if (param.isHasCnt()) {
                if (param.getMaxTime() == null) {
                    return RetCode.MAX_CNT_NULL;
                }
                if (param.getMaxTime() == 0L) {
                    return RetCode.MAX_CNT_ZERO;
                }

                if (chkDb) {
                    Long actualRunCnt = rparam.getRunedCnt();
                    if (actualRunCnt == null || actualRunCnt < 0L) {
                        actualRunCnt = 0L;
                        rparam.setRunedCnt(actualRunCnt);
                    }
                    if (actualRunCnt >= param.getMaxTime()) {
                        return RetCode.RUN_CNT_REACH_MAX_CNT;
                    }
                }
            }
        }

        //check implement interface
        if (param.isHasParam()) {
            //check target implement execute(String )
            if (!ReflectClassUtil.chkAImplementB(target, ITimingTaskParam.class)) {
                return RetCode.HAS_PARAM_NOT_IMPLEMENT_INF;
            }
        } else {
            //check target implement execute()
            if (!ReflectClassUtil.chkAImplementB(target, ITimingTask.class)) {
                return RetCode.NO_PARAM_NOT_IMPLEMENT_INF;
            }
        }

        if (param.getCrtTm() == null || param.getCrtTm() < 0) {
            param.setCrtTm(0L);
        }

        //check is in mutex
        if (chkDb) {
            if (rparam.getLastStartMs() == null) {
                rparam.setLastStartMs(0L);
            }
            if (timingLockHelper.hasLock(rparam.getMutexTm())) {
                LogDbUtil.error(TimingConstant.MODULE_TIMING, "check task ,task locking");
                return RetCode.HAS_LOCK;
            }

            //no lock,check status valid,check alive time,is some manager incharging
            if (TimingConstant.validCanExeStatus(rparam.getStatus())) {
                Long now = DateUtil.getNowTS();
                //if(chkDb){
                Long aliveTm = rparam.getAliveTm();
                if (aliveTm == null) {
                    return RetCode.SUCCESS;
                }
                Long interval = now - aliveTm;
                if (LOG.isDebugEnabled()) {
                    LOG.debug("check task interval {},compare to {}", interval, TimingConstant.NOT_ALIVE_REF);
                }
                if (interval > TimingConstant.NOT_ALIVE_REF) {
                    return RetCode.SUCCESS;
                } else {
                    return RetCode.STILL_ALIVE;
                }
            } else {
                return RetCode.STATUS_CANNOT_START;
            }
        } else {
            return RetCode.SUCCESS;
        }
    }


    /**
     * get execute method
     *
     * @param target
     * @param param
     * @return
     */
    public Method getExeMethod(Object target, boolean param) {
        if (param) {
            return ReflectMethodUtil.getDeclaredMethod(target, TimingConstant.METHOD_EXECUTE, String.class);
        } else {
            return ReflectMethodUtil.getDeclaredMethod(target, TimingConstant.METHOD_EXECUTE);
        }
    }
}
