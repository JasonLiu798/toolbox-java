package com.atjl.dbtiming.task;

import com.atjl.dbtiming.domain.biz.TaskEnum;

import com.atjl.logdb.api.LogDbUtil;
import com.atjl.util.common.DateUtil;
import com.atjl.util.json.JSONFastJsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.atjl.dbtiming.helper.TimingInnerManager;
import com.atjl.dbtiming.domain.constant.TimingConstant;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.ScheduledFuture;

/**
 * task base class
 *
 * @author JasonLiu
 */
public abstract class TimingTaskBase implements Runnable {
    private static Logger LOG = LoggerFactory.getLogger(TimingTaskBase.class);
    /**
     * ################# basis ##################
     */
    /**
     * task db pk
     */
    protected Long tid;
    /**
     * task key
     */
    protected String key;
    /**
     * task type
     */
    protected TaskEnum type;
    /**
     * for cancel
     */
    protected ScheduledFuture future;
    /**
     * timing core helper
     */
    protected TimingInnerManager innerManager;

    /**
     * ################ timing #####################
     */
    /**
     * first delay time
     */
    protected long delayTime = 0;
    /**
     * interval time
     */
    protected long interval = 0;

    /**
     * ############## executor ###################
     */
    protected Object target;

    protected Method exeMethod;
    /**
     * ############## param ####################
     */
    /**
     * execute exeMethod got param
     */
    protected boolean hasParam = false;
    /**
     * parameter
     */
    protected String param;

    /**
     * ############### status #####################
     */
    /**
     * is running
     */
    protected volatile boolean running = false;
    /**
     * last start time
     */
    protected long lastStartTime = 0;
    /**
     * last stop time
     */
    protected long lastStopTime = 0;
    /**
     * runed counter
     */
    protected Long runnedCounter = 0L;
    /**
     * last run success
     */
    protected boolean lastExeSucc = true;
    /**
     * is persist to db
     * inner thread not persist
     */
    protected boolean persist = true;
    // is totally end
    protected boolean end = false;
    //submit to pool time
    protected long submitTm;


    /**
     * constructor
     *
     * @param tid
     */
    public TimingTaskBase(Long tid, TimingInnerManager helper, Object target, Method method) {
        this.tid = tid;
        this.innerManager = helper;
        this.target = target;
        this.exeMethod = method;
        this.submitTm = DateUtil.getNowTS()*1000;
    }

    /**
     * run before
     * for sys
     */
    public void before() {
        if (persist) {
            innerManager.updateStatus(this.tid, TimingConstant.STATUS_EXECUTING);
        }
        running = true;
        lastStartTime = System.currentTimeMillis();
        lastExeSucc = true;

    }

    public void execute() {
        try {
            if (hasParam) {
                exeMethod.invoke(target, param);
            } else {
                exeMethod.invoke(target);
            }
        } catch (IllegalAccessException | InvocationTargetException e) {
            LogDbUtil.error(TimingConstant.MODULE_TIMING, "execute exception", e);
            if (LOG.isDebugEnabled()) {
                LOG.debug("timing invoke exeMethod error {}", e.getMessage());
            }
        }
    }

    /**
     * for runnable
     */
    @Override
    public void run() {
        if (LOG.isDebugEnabled() && tid > 0) {
            LOG.debug("task run before bf {}", JSONFastJsonUtil.objectToJson(this));
        }
        before();
        if (LOG.isDebugEnabled() && tid > 0) {
            LOG.debug("task run before af {}", JSONFastJsonUtil.objectToJson(this));
        }

        try {
            execute();
        } catch (Exception e) {
            LogDbUtil.error(TimingTaskBase.class, "task execute error", e);
            lastExeSucc = false;
        }

        if (LOG.isDebugEnabled() && tid > 0) {
            LOG.debug("task run after bf {}", JSONFastJsonUtil.objectToJson(this));
        }
        after();
        if (LOG.isDebugEnabled() && tid > 0) {
            LOG.debug("task run after af {}", JSONFastJsonUtil.objectToJson(this));
        }
        //if persist to db
        if (persist) {
            afterPersist();
        }

        //end thread
        if (end) {
            boolean canRes = innerManager.cancelTask(tid);//end thread
            if (LOG.isDebugEnabled()) {
                LOG.debug("cancel task res {}", canRes);
            }
        }
    }

    /**
     * run after
     */
    public void after() {
        lastStopTime = System.currentTimeMillis();
        running = false;
        runnedCounter++;
    }

    /**
     * persist to db
     */
    public void afterPersist() {
        innerManager.saveHistory(this);//persist history
        if (end) {
            innerManager.updateStatus(this.tid, TimingConstant.STATUS_END);
        } else {
            innerManager.updateStatus(this.tid, TimingConstant.STATUS_WAITING);
        }
    }


    /**
     * ################### getter & setter ################################
     */
    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public Long getTid() {
        return tid;
    }

    public void setTid(Long tid) {
        this.tid = tid;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public TaskEnum getType() {
        return type;
    }

    public void setType(TaskEnum type) {
        this.type = type;
    }

    public long getDelayTime() {
        return delayTime;
    }

    public void setDelayTime(long delayTime) {
        this.delayTime = delayTime;
    }

    public long getLastStartTime() {
        return lastStartTime;
    }

    public void setLastStartTime(long lastStartTime) {
        this.lastStartTime = lastStartTime;
    }

    public long getLastStopTime() {
        return lastStopTime;
    }

    public void setLastStopTime(long lastStopTime) {
        this.lastStopTime = lastStopTime;
    }

    public ScheduledFuture getFuture() {
        return future;
    }

    public boolean isLastExeSucc() {
        return lastExeSucc;
    }

    public void setLastExeSucc(boolean lastExeSucc) {
        this.lastExeSucc = lastExeSucc;
    }

    public boolean isEnd() {
        return end;
    }

    public void setEnd(boolean end) {
        this.end = end;
    }

    public long getSubmitTm() {
        return submitTm;
    }

    public void setSubmitTm(long submitTm) {
        this.submitTm = submitTm;
    }

    public TimingInnerManager getInnerManager() {
        return innerManager;
    }

    public void setInnerManager(TimingInnerManager innerManager) {
        this.innerManager = innerManager;
    }

    public Long getRunnedCounter() {
        return runnedCounter;
    }

    public void setRunnedCounter(Long runnedCounter) {
        this.runnedCounter = runnedCounter;
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

    public long getInterval() {
        return interval;
    }

    public void setInterval(long interval) {
        this.interval = interval;
    }

    public Object getTarget() {
        return target;
    }

    public void setTarget(Object target) {
        this.target = target;
    }

//    public Method getExeMethod() {
//        return exeMethod;
//    }

    public void setExeMethod(Method exeMethod) {
        this.exeMethod = exeMethod;
    }

    public boolean isPersist() {
        return persist;
    }

    public void setPersist(boolean persist) {
        this.persist = persist;
    }

    public void setFuture(ScheduledFuture future) {
        this.future = future;
    }
}
