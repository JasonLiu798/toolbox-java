package com.atjl.util.thread.task;

import com.atjl.util.thread.ThreadManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 */
public abstract class BaseTask implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(BaseTask.class);

    private long id;
    private ThreadManager threadManager;

    private State state;

    public static enum State {
        NEW, RUNNING, STOP, EXCEP_STOP
    }

    public BaseTask() {
        this.id = System.nanoTime();
        state = State.NEW;
    }

    public BaseTask(long id) {
        this.id = id;
        state = State.NEW;
    }

    public String getStateValue() {
        return this.state.toString();
    }

    @Override
    public final void run() {
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

}
