package com.atjl.dbtiming.helper;

import com.atjl.common.context.DevContext;
import com.atjl.config.api.ConfigConstant;
import com.atjl.config.api.ConfigUtil;
import com.atjl.dbtiming.core.TimingContext;
import com.atjl.dbtiming.domain.gen.GenTaskRuned;
import com.atjl.dbtiming.api.domain.RespDto;
import com.atjl.dbtiming.api.domain.RetCode;
import com.atjl.dbtiming.api.TimingService;
import com.atjl.dbtiming.task.*;
import com.atjl.logdb.api.LogDbUtil;
import com.atjl.util.collection.CollectionUtil;
import com.atjl.util.common.CovertUtil;
import com.atjl.util.common.DateUtil;
import com.atjl.util.common.ReflectUtil;
import com.atjl.util.json.JSONFastJsonUtil;
import com.atjl.util.reflect.ReflectMethodUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import com.atjl.dbtiming.domain.gen.GenTask;
import com.atjl.dbtiming.domain.gen.GenTaskHistory;
import com.atjl.dbtiming.domain.constant.TimingConstant;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.util.*;
import java.util.concurrent.*;

/**
 * timing inner manager,for inner thread call
 */
@Component
public class TimingInnerManager {
    private static final Logger logger = LoggerFactory.getLogger(TimingInnerManager.class);
    @Resource
    TimingDbHelper timingDbHelper;
    @Resource
    TimingService timingManager;
    @Resource
    TimingCommonHelper timingCommonHelper;

    /**
     * init exeMethod,call after spring init
     */
    public void init() {
        int poolSize = getPoolSize();
        if (!TimingContext.buildTaskPool(poolSize)) {
            //throw new TimingException("init pool fail");
            LogDbUtil.error(TimingConstant.MODULE_TIMING, "init pool error");
            return;
        }
        //init monitor threads,testcase
        initMonitThread();
        //init health thread
        RetCode healthRet = initHealthThread();
        if (healthRet != RetCode.SUCCESS) {
            LogDbUtil.error(TimingConstant.MODULE_TIMING, "init health thread fail," + healthRet);
            return;            //if health thread fail ,no need start other thread
        }
        initClearThread();
        initResetCounterThread();
    }


    private int getPoolSize() {
        int poolSize = TimingConstant.DFT_POOL_SIZE;
        try {
            poolSize = CovertUtil.covert(ConfigUtil.get(ConfigConstant.CONF_SERVICE_USE_DB_PLAIN, TimingConstant.POOL_SIZE_PK), Integer.class, TimingConstant.DFT_POOL_SIZE);
        } catch (Exception e) {
            logger.error("get pool size error,use {},{}", poolSize, e);
            poolSize = TimingConstant.DFT_POOL_SIZE;
        }
        return poolSize;
    }


    private void initMonitThread() {
        if (DevContext.isTest()) {
            RetCode monitorRet = initMonitorThread();
            if (monitorRet != RetCode.SUCCESS) {
                LogDbUtil.error(TimingConstant.MODULE_TIMING, "init monitor thread fail," + monitorRet);
            } else {
                if (logger.isDebugEnabled()) {
                    logger.debug("init monitor thread succ");
                }
            }
        }
    }

    public void shutDown() {
        TimingContext.deatroyPool();
    }

    public boolean taskExist(Long tid) {
        return TimingContext.taskExist(tid);
    }


    private boolean chkRexecTask(List<GenTask> notEndTasks) {
        if (CollectionUtil.isEmpty(notEndTasks)) {
            if (logger.isDebugEnabled()) {
                logger.debug("no task to restart");
            }
            return false;
        } else {
            if (logger.isDebugEnabled()) {
                logger.debug("not end tasks,but in db,not update alive tm {}", JSONFastJsonUtil.objectToJson(notEndTasks));
            }
            return true;
        }
    }

    /**
     * re put no incharging task to pool
     */
    public void reExecTask() {
        List<GenTask> notEndTasks = timingDbHelper.getTaskNotEndButNoManagerProcessing();
        if (!chkRexecTask(notEndTasks)) {
            return;
        }

        //check is dead in pool
        for (GenTask t : notEndTasks) {
            Long tid = t.getTid();

            boolean exist = TimingContext.taskExist(tid);
            if (exist) {
                //still in pool,but dead
                if (!TimingContext.cancleTask(tid)) {
                    //cancel fail,clear map
                    TimingContext.forceClearMap(tid);
                    LogDbUtil.error(TimingConstant.MODULE_TIMING, "clear task fail,task:" + JSONFastJsonUtil.objectToJson(t));
                    continue;
                }
            }

            //not in pool,re execute
            String type = null;//t.getConfType();
            switch (type) {
                case TimingConstant.TP_CRON:
                    timingManager.execCronTask(tid);
                    break;
                case TimingConstant.TP_DYN_FIXRATE_CNT:
                case TimingConstant.TP_DYN_FIXRATE_CNT_COND:
                    timingManager.reExecDynamicTask(tid);
                    break;
                default:
                    LogDbUtil.error(TimingConstant.MODULE_TIMING, "db task unsupported type,tid:" + tid + ",tp:" + type);
                    backupAndDelTask(tid);//unsupport type ,del task
                    break;
            }
        }
    }


    /**
     * init monitor thread
     */
    private RetCode initMonitorThread() {
        return submitFixRate(
                TimingConstant.MONITOR_THREAD_ID,
                TimingConstant.MONITOR_THREAD_KEY,
                new MonitorTask(),
                true,//forever
                false,//param
                false,//cond
                false,//persist
                "",//param
                TimingConstant.DFT_MONITOR_DELAY,//delay
                TimingConstant.DFT_MONITOR_RATE,//interval
                -1,//max time
                0L
        );
    }

    /**
     * clea task
     *
     * @return
     */
    private RetCode initClearThread() {
        ClearTimingTask clearTask = new ClearTimingTask();
        clearTask.setTimingInnerManager(this);

        long interval = CovertUtil.covert(ConfigUtil.get(ConfigConstant.CONF_SERVICE_USE_DB_PLAIN, TimingConstant.DFT_CLEAR_INTERVAL_PK), Long.class, TimingConstant.DFT_CLEAR_RATE);

        if (DevContext.isTest()) {//for test temp setting
            interval = 70;
        }

        RetCode clrRet = submitFixRate(
                TimingConstant.CLEAR_THREAD_ID,
                TimingConstant.CLEAR_THREAD_KEY,
                clearTask,
                true,//forever
                false,//param
                false,//cond
                false,//persist
                null,//param
                interval * 1000,//
                interval * 1000,
                -1,
                0L
        );
        if (clrRet != RetCode.SUCCESS) {
            LogDbUtil.error(TimingConstant.MODULE_TIMING, "init clear thread fail," + clrRet);
        }
        return clrRet;
    }

    private RetCode initResetCounterThread() {
        long interval = CovertUtil.covert(ConfigUtil.get(ConfigConstant.CONF_SERVICE_USE_DB_PLAIN, TimingConstant.DFT_RESET_COUNT_INTERVAL_PK), Long.class, TimingConstant.DFT_RESET_CNT_RATE);

//        long interval = dictService.getLongValueByPathKey(TimingConstant.DFT_RESET_COUNT_INTERVAL_PK, TimingConstant.DFT_RESET_CNT_RATE);
        CounterResetTask ct = new CounterResetTask();
        RetCode restRet = submitFixRate(
                TimingConstant.RESET_CNT_THREAD_ID,
                TimingConstant.RESET_CNT_THREAD_KEY,
                ct,
                true,//forever
                false,//param
                false,//cond
                false,//persist
                null,//param
                interval * 1000,//delay
                interval * 1000,//interval
                TimingConstant.PAGE_MAX,//max time
                0L
        );
        if (restRet != RetCode.SUCCESS) {
            LogDbUtil.error(TimingConstant.MODULE_TIMING, "init resetCounter thread fail," + restRet);
        }
        return restRet;
    }

    /**
     * health task
     * 1.restart dead task thread
     * 2.update task live time
     */
    private RetCode initHealthThread() {
        HealthTask ht = new HealthTask();
        ht.setTimingInnerManager(this);

//        long interval = dictService.getLongValueByPathKey(TimingConstant.DFT_HEART_BEAT_PK, TimingConstant.DFT_HEART_BEAT);

        long interval = CovertUtil.covert(ConfigUtil.get(ConfigConstant.CONF_SERVICE_USE_DB_PLAIN, TimingConstant.DFT_HEART_BEAT_PK), Long.class, TimingConstant.DFT_HEART_BEAT);


        if (interval != TimingConstant.DFT_HEART_BEAT) {
            if (interval < TimingConstant.MIN_HEART_BEAT || interval > TimingConstant.MAX_HEART_BEAT) {
                //parameter out of range
                interval = TimingConstant.DFT_HEART_BEAT;
            } else {
                //reverse update
                TimingConstant.NOT_ALIVE_INTERVAL = interval;
                TimingConstant.NOT_ALIVE_REF = interval + TimingConstant.JITTER;
            }
        }
        LogDbUtil.info(TimingConstant.MODULE_TIMING, "heart beat interval " + interval);
        return submitFixRate(
                TimingConstant.HEALTH_THREAD_ID,
                TimingConstant.HEALTH_THREAD_KEY,
                ht,
                true,//forever
                false,//param
                false,//cond
                false,//persist
                null,//param
                interval * 1000,//delay
                interval * 1000,//interval
                TimingConstant.PAGE_MAX,//max time
                0L
        );
    }


    /**
     * update task status
     *
     * @param tid
     * @param status
     */
    public void updateStatus(Long tid, String status) {
        timingDbHelper.updateTaskStatus(tid, status);
    }

    /**
     * save task execute history
     *
     * @param t
     */
    public void saveHistory(TimingTaskBase t) {
        timingDbHelper.saveHistory(t);
    }

    /**
     * is valid tid
     *
     * @param tid
     * @return
     */
    private RetCode validTid(Long tid) {
        //check duplicate
        if (tid == null) {
            return RetCode.TID_NULL;
        }
        if (TimingContext.taskExist(tid)) {
            return RetCode.TID_POOL_EXIST;
        }
        return RetCode.SUCCESS;
    }

    /**
     * submit delay task to pool
     *
     * @return public boolean submitDelayTask(ITaskExecute task, long delayMs) {
     * //        if (!validTask(task)) {
     * //            return false;
     * //        }
     * //        String tid = task.getTid();
     * //timingTaskHelper.
     * DelayTask innerTask = new DelayTask(tid, this, task);
     * submitDelay(innerTask, delayMs);
     * return true;
     * }
     */

    public boolean reSubmitCronTask(CronTask t) {
        submitDelay(t, t.getDelayTime(), false);
        return true;
    }


    public void updateManagerLiveTm() {
        timingDbHelper.updateManagerAlive(timingManager.getManagerId());
    }

    /**
     * batch update tasks alive time and manager
     */
    public void updateLiveTm() {
        String managerId = timingManager.getManagerId();
        List<Long> tasks = TimingContext.getTaskList();
        timingDbHelper.updateTaskLiveTm(tasks, managerId);
    }

    /**
     * submit cron task to pool
     *
     * @param cronExpression
     * @return
     * @throws ParseException
     */
    public RespDto<Long> submitCronTask(Long tid, String key, Object target, String cronExpression, long runedCnt, Long lastStartTm, Long lastEndTm) {
        if (validTid(tid) != RetCode.SUCCESS) {
            return RespDto.buildFail();
        }
        Method exeMethod = timingCommonHelper.getExeMethod(target, false);
        if (exeMethod == null) {
            logger.debug("get exe method null");
            return RespDto.buildFail();
        }
        CronTask innerTask = null;
        try {
            innerTask = new CronTask(tid, this, target, exeMethod, cronExpression);
            if (lastStartTm != null) {
                innerTask.setLastStartTime(lastStartTm);
            }
            if (lastEndTm != null) {
                innerTask.setLastStopTime(lastEndTm);
            }
        } catch (ParseException e) {
            return RespDto.buildFail(RetCode.CRON_EXPESSION_INVALID);
        }

        innerTask.setKey(key);
        innerTask.setRunnedCounter(runedCnt);
        Long delayMs = innerTask.cron2delay();
        logger.debug("cron task first delay {}", delayMs);
        submitDelay(innerTask, delayMs, true);
        return RespDto.buildOkLong(delayMs);
    }

    /**
     * add fix rate execute until conditon reach task
     *
     * @param delayMs
     * @param intervalMs
     * @return
     */
    public RetCode submitFixRate(Long tid,
                                 String key,
                                 Object target,
                                 boolean forever,
                                 boolean hasParam,
                                 boolean hasCond,
                                 boolean persist,
                                 String param,
                                 long delayMs,
                                 long intervalMs,
                                 long maxTime,
                                 long runedCnt) {
        if (logger.isDebugEnabled()) {
            logger.debug("submit fix rate task,tid {},key {},has param {},hasCond {},persist {},forever {},param {},delay {},interval {},maxtime {},runCnt {}", tid, key, hasParam, hasCond, persist, forever, param, delayMs, intervalMs, maxTime, runedCnt);
        }

        RetCode chkVid = validTid(tid);
        if (chkVid != RetCode.SUCCESS) {
            return chkVid;
        }
        if (target == null) {
            return RetCode.SERVICE_NULL;
        }

        Method exeMethod = timingCommonHelper.getExeMethod(target, hasParam);
        if (exeMethod == null) {
            logger.debug("get exe {} exeMethod null", hasParam);
        }

        Method condMethod = null;
        if (hasCond) {
            condMethod = ReflectMethodUtil.getDeclaredMethod(target, TimingConstant.METHOD_COND, String.class);
            if (condMethod == null) {
                logger.debug("get cond exeMethod null");
                return RetCode.COND_METHOD_NULL;
            }
        }

        FixRateTask innerTask = null;
        if (forever) {
            innerTask = new FixRateTask(tid, this,
                    target, exeMethod);
        } else {
            if (hasCond) {
                innerTask = new FixRateCondTask(
                        tid, this, target, exeMethod,
                        condMethod, maxTime);
            } else {
                innerTask = new FixRateCntTask(
                        tid, this,
                        target, exeMethod, maxTime);
            }
        }

        if (logger.isDebugEnabled()) {
            logger.debug("new task class {}", innerTask.getClass());
        }

        innerTask.setHasParam(hasParam);
        if (hasParam) {
            innerTask.setParam(param);
        }
        innerTask.setKey(key);
        innerTask.setPersist(persist);
        innerTask.setRunnedCounter(runedCnt);
        submitFixRate(innerTask, delayMs, intervalMs);
        return RetCode.SUCCESS;
    }


    /**
     * submit delay task
     *
     * @param innerTask
     * @param delayMs
     */
    private void submitDelay(TimingTaskBase innerTask, long delayMs, boolean isNew) {
        innerTask.setDelayTime(delayMs);
        ScheduledFuture future = TimingContext.executorService.schedule(innerTask, delayMs, TimeUnit.MILLISECONDS);
        innerTask.setFuture(future);
        TimingContext.addTask(innerTask.getTid(), innerTask);
        if (isNew) {
            TimingContext.incrementTaskCnt();
        }
    }


    /**
     * submit fix rate task
     *
     * @param innerTask
     * @param delayMs
     * @param interval
     */
    private boolean submitFixRate(FixRateTask innerTask, long delayMs, long interval) {
        innerTask.setDelayTime(delayMs);
        innerTask.setInterval(interval);

        if (logger.isDebugEnabled()) {
            logger.debug("scheduleAtFixedRate delay {},interval {}", delayMs, interval);
        }
        ScheduledFuture future = TimingContext.executorService.scheduleAtFixedRate(innerTask, delayMs, interval, TimeUnit.MILLISECONDS);

        innerTask.setFuture(future);
        TimingContext.addTask(innerTask.getTid(), innerTask);
        TimingContext.incrementTaskCnt();

        if (logger.isDebugEnabled()) {
            logger.debug("scheduleAtFixedRate succ,tid {},delay {},interval {}", innerTask.getTid(), delayMs, interval);
        }

        LogDbUtil.info(TimingConstant.MODULE_TIMING, "scheduleAtFixedRate succ tid " + innerTask.getTid() + ",delay " + delayMs + ",interval " + interval);

        if (future == null) {
            return false;
        }
        return true;
    }

    /**
     * cancel task
     *
     * @param tid
     */
    public boolean cancelTask(Long tid) {
        return TimingContext.cancleTask(tid);
    }


    /**
     * clear
     * ts_task
     * ts_task_history
     */
    public void clearTask() {
        //clear cron task histories
        clearCronTaskHistory(TimingConstant.CLEAR_DATA_LESS);

        //get stopped or invalid tasks
        List<GenTask> delTasks = timingDbHelper.getTasksEndOrInvalid();
        if (logger.isDebugEnabled()) {
            logger.debug("del tasks {}", JSONFastJsonUtil.objectToJson(delTasks));
        }
        //del stopped and invalid task,cancel task
        delTaskAndBackup(delTasks);
        //clear task not in db
        clearTaskNotInDbButInPool();
    }

    /**
     * clear cron task's running history
     */
    public void clearCronTaskHistory(Long dateLess) {
        List<GenTask> cronTasks = timingDbHelper.getValidCronTasks();
        if (logger.isDebugEnabled()) {
            logger.debug("valid cron tasks {}", JSONFastJsonUtil.objectToJson(cronTasks));
        }
        if (!CollectionUtil.isEmpty(cronTasks)) {
            List<Long> cronTids = new ArrayList<>(cronTasks.size());

            for (GenTask ct : cronTasks) {
                cronTids.add(ct.getTid());
            }

            int res = timingDbHelper.deleteTaskHistories(cronTids, (DateUtil.getNowTS() - dateLess) * 1000);
            if (logger.isDebugEnabled()) {
                logger.debug("clear histories {}", res);
            }
        }
    }

    /**
     * backup task,and del task
     *
     * @param tid
     */
    public void backupAndDelTask(Long tid) {
        GenTask t = timingDbHelper.getTaskGenById(tid);
        if (t == null) {
            LogDbUtil.error(TimingConstant.MODULE_TIMING, "del task db null,tid " + tid);
            return;
        }
        GenTaskRuned taskRuned = new GenTaskRuned();
        //(Object source, Object target, GetClzOpt opt, boolean allowNull, String[] blackList, String[] whiteList) {
        ReflectUtil.copyField(t, taskRuned, ReflectUtil.GetClzOpt.ALL, true, new String[]{"crtTm"}, null);
        Long crtTm = t.getCrtTm();
        if (crtTm != null) {
            taskRuned.setCrtTm(null);//DateUtil.ts2Date(crtTm));
        }
        List<Long> tids = CollectionUtil.array2List(new Long[]{tid});
        timingDbHelper.addTaskRuned(taskRuned);
        timingDbHelper.deleteTasks(tids);
    }

    /**
     * batch backup to ts_task_runned
     */
    public void delTaskAndBackup(List<GenTask> delTasks) {
        if (!CollectionUtil.isEmpty(delTasks)) {
            if (logger.isDebugEnabled()) {
                logger.debug("del tasks {}", JSONFastJsonUtil.objectToJson(delTasks));
            }

            Set<Long> duplicates = new HashSet<>();
            List<Long> delTaskIdList = new LinkedList<>();
            for (GenTask t : delTasks) {
                Long tid = t.getTid();
                if (duplicates.contains(tid)) {//not process duplicate
                    continue;
                }

                //still in pool,cancel the task
                if (TimingContext.taskExist(tid)) {
                    if (logger.isDebugEnabled()) {
                        logger.debug("dead task still in pool");
                    }
                    boolean cancelRes = TimingContext.cancleTask(tid);
                    if (!cancelRes) {
                        TimingContext.forceClearMap(tid);
                    }
                    LogDbUtil.error(TimingConstant.MODULE_TIMING, "task dead or invalid still in pool,tid " + t.getTid() + ",cancel res " + cancelRes);
                }//else not in pool,continue

                List<GenTaskHistory> histories = timingDbHelper.getTaskHistory(t.getTid());
                long maxStart = 0;
                long minStart = Long.MAX_VALUE;
                long maxEnd = 0;
                long minEnd = Long.MAX_VALUE;
                long runed = 0;
                if (!CollectionUtil.isEmpty(histories)) {
                    runed = histories.size();
                    for (GenTaskHistory h : histories) {
                        if (h.getStartTm() > maxStart) {
                            maxStart = h.getStartTm();
                        }
                        if (h.getStartTm() < minStart) {
                            minStart = h.getStartTm();
                        }
                        if (h.getEndTm() > maxEnd) {
                            maxEnd = h.getEndTm();
                        }
                        if (h.getEndTm() < minEnd) {
                            minEnd = h.getEndTm();
                        }
                    }
                }


                GenTaskRuned taskRuned = new GenTaskRuned();
//                ReflectUtil.copyField(t, taskRuned, new String[]{"crtTm"}, true);

                ReflectUtil.copyField(t, taskRuned, ReflectUtil.GetClzOpt.ALL, true, new String[]{"crtTm"}, null);

                taskRuned.setFirstStartTm(minStart);
                taskRuned.setFirstEndTm(minEnd);
                taskRuned.setLastStartTm(maxStart);
                taskRuned.setLastEndTm(maxEnd);
                taskRuned.setRunCnt(runed);
                Long crtTm = t.getCrtTm();
                if (crtTm != null) {
                    taskRuned.setCrtTm(null);//DateUtil.ts2Date(crtTm));
                }
                if (logger.isDebugEnabled()) {
                    logger.debug("insert taskRunned {}", JSONFastJsonUtil.objectToJson(taskRuned));
                }
                //backup to ts_task_runned
                timingDbHelper.addTaskRuned(taskRuned);

                //for check duplicate tid
                duplicates.add(t.getTid());
                //for del
                delTaskIdList.add(t.getTid());
            }

            if (logger.isDebugEnabled()) {
                logger.debug("del task and histories {}", delTaskIdList);
            }
            //del ts_task
            timingDbHelper.deleteTasks(delTaskIdList);
            //del ts_task_history
            timingDbHelper.deleteTaskHistories(delTaskIdList);
        }
    }

    /**
     * clear task not in db,but in pool
     */
    public void clearTaskNotInDbButInPool() {
        //clear task not in db
        List<Long> bizTaskInPool = TimingContext.getTaskListBiz();
        if (logger.isDebugEnabled()) {
            logger.debug("task in pool {}", bizTaskInPool);
        }
        if (!CollectionUtil.isEmpty(bizTaskInPool)) {
            List<GenTask> validTasks = timingDbHelper.getTasksByValid(TimingConstant.Y);
            if (!CollectionUtil.isEmpty(validTasks)) {
                Set<Long> dbSet = new HashSet<>();
                for (GenTask gt : validTasks) {
                    dbSet.add(gt.getTid());
                }
                if (logger.isDebugEnabled()) {
                    logger.debug("valid task in db {}", dbSet);
                }
                for (Long pTid : bizTaskInPool) {
                    if (!dbSet.contains(pTid)) {//task not in db,but in pool
                        boolean cancleTaskRes = TimingContext.cancleTask(pTid);
                        LogDbUtil.error(TimingConstant.MODULE_TIMING, "task not in db,but in pool,tid " + pTid + ",cancel res " + cancleTaskRes);
                    }
                }
            }
        }
    }

}
