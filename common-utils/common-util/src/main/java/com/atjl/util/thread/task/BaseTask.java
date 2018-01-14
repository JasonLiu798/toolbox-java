package com.atjl.util.thread.task;

import com.atjl.util.common.DateUtil;
import com.atjl.util.thread.ThreadManager;
import io.swagger.annotations.ApiModelProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 */
public abstract class BaseTask implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(BaseTask.class);

    @ApiModelProperty(value = "线程编号 thread id")
    private String id;

    @ApiModelProperty(value = "线程管理器 thread manager")
    private ThreadManager threadManager;

    @ApiModelProperty(value = "线程状态 thread state")
    private State state;

    @ApiModelProperty(value = "开始时间戳，start timestamp")
    private Long startTs;

    public enum State {
        NEW, RUNNING, STOP, EXCEP_STOP
    }

    public BaseTask() {
        this.id = String.valueOf(System.nanoTime());
        state = State.NEW;
    }

    public BaseTask(String id) {
        this.id = id;
        state = State.NEW;
    }

    public String getStateValue() {
        return this.state.toString();
    }

    @Override
    public final void run() {
        startTs = DateUtil.getNowTS();
        state = State.RUNNING;
        try {
            bizRun();
        } catch (Exception e) {
            state = State.EXCEP_STOP;
            logger.error("task run fail {}", e);
        }
        if (state == State.RUNNING) {
            state = State.STOP;//正常运行完毕
        }
        if (threadManager != null) {
            threadManager.rmFromMap(this.id);
            logger.debug("rm from map {}", this.id);
        }
    }

    /**
     * 业务逻辑
     */
    protected abstract void bizRun();

    public String getState() {
        return state.toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ThreadManager getThreadManager() {
        return threadManager;
    }

    public void setThreadManager(ThreadManager threadManager) {
        this.threadManager = threadManager;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Long getStartTs() {
        return startTs;
    }

    public void setStartTs(Long startTs) {
        this.startTs = startTs;
    }
}
