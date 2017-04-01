package com.jason798.timing;

import com.jason798.timing.api.TimingException;
import com.jason798.timing.entity.TimingConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * core schedule pool
 */
@Component
public class TimingCoreHelper {
    private static Logger LOG = LoggerFactory.getLogger(TimingCoreHelper.class);

    @Resource
    DictService dictService;

    /**
     * init method,call after spring init
     */
    public void init() {
        int poolSize = TimingConstant.DFT_POOL_SIZE;
        try {
            poolSize = dictService.getIntValueByPathKey(TimingConstant.POOL_SIZE_PK, TimingConstant.DFT_POOL_SIZE);
        } catch (Exception e) {
            LOG.error("get pool size error,use {}", poolSize);
        }
        if(!TimingContext.buildTaskPool(poolSize)){
            throw new TimingException("init pool fail");
        }
        ITimingTaskSimple monitorTask = new MonitorTask();
        if(!submitFixRate(monitorTask,TimingConstant.DFT_MONITOR_DELAY,TimingConstant.DFT_MONITOR_RATE)){
            LOG.error("monitor thread submit fail");
        }
    }

    /**
     * is valid task
     * @param task
     * @return
     */
    private boolean validTask(ITimingTaskSimple task) {
        String tid = task.getTid();
        /**
         * check duplicate
         */
        if (tid == null || TimingContext.taskExist(tid)) {
            return false;
        }
        if (task == null) {
            LOG.error("add fixRateCond task,service null");
            return false;
        }
        return true;
    }

    /**
     * submit delay task to pool
     * @param task
     * @param delayMs
     * @return
     */
    public boolean submitDelayTask(ITimingTaskSimple task, long delayMs) {
        if (!validTask(task)) {
            return false;
        }
        String tid = task.getTid();
        DelayTask innerTask = new DelayTask(tid, task);
        submitDelay(innerTask,delayMs);
        return true;
    }


    /**
     * add fix rate task
     *
     * @param task
     * @param delayMs
     * @param intervalMs
     * @return
     */
    public boolean submitFixRate(ITimingTaskSimple task, long delayMs, long intervalMs) {
        if (!validTask(task)) {
            return false;
        }
        String tid = task.getTid();
        FixRateTask innerTask = new FixRateTask(tid, task);
        submitFixRate(innerTask,delayMs,intervalMs);
        return true;
    }

    /**
     * add fix rate execute until conditon reach task
     *
     * @param task
     * @param delayMs
     * @param intervalMs
     * @return
     */
    public boolean submitFixRateCond(ITimingTaskCond task, long delayMs, long intervalMs) {
        if (!validTask(task)) {
            return false;
        }
        String tid = task.getTid();

        FixRateCondTask innerTask = new FixRateCondTask(tid, task);
        submitFixRate(innerTask,delayMs,intervalMs);
        return true;
    }

    /**
     * submit delay task
     * @param innerTask
     * @param delayMs
     */
    private void submitDelay(DelayTask innerTask,long delayMs){
        innerTask.setDelayTime(delayMs);
        ScheduledFuture future = TimingContext.executorService.schedule(innerTask, delayMs, TimeUnit.MILLISECONDS);
        innerTask.setFuture(future);
        TimingContext.addTask(innerTask.getTid(),innerTask);
        TimingContext.incrementTaskCnt();
    }

    /**
     * submit fix rate task
     * @param innerTask
     * @param delayMs
     * @param interval
     */
    private void submitFixRate(FixRateTask innerTask,long delayMs,long interval){
        innerTask.setDelayTime(delayMs);
        innerTask.setInterval(interval);
        ScheduledFuture future = TimingContext.executorService.scheduleAtFixedRate(innerTask, delayMs, interval, TimeUnit.MILLISECONDS);
        innerTask.setFuture(future);
        TimingContext.addTask(innerTask.getTid(),innerTask);
        TimingContext.incrementTaskCnt();
    }

    public DictService getDictService() {
        return dictService;
    }

    public void setDictService(DictService dictService) {
        this.dictService = dictService;
    }
}
