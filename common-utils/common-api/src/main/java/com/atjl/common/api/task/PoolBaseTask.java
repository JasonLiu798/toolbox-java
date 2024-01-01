package com.atjl.common.api.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

/**
 * base task
 */
public abstract class PoolBaseTask implements Serializable {
    private static final Logger logger = LoggerFactory.getLogger(PoolBaseTask.class);

    private static final long serialVersionUID = 3525221586178055369L;

    /**
     * 是否启动
     */
    protected boolean start = true;

    /**
     * 唯一编号
     */
    protected String id;

    public PoolBaseTask() {
        logger.debug("new " + this.getClass());
    }

    public PoolBaseTask(String id) {
        this.id = id;
    }

    public boolean isStart() {
        return start;
    }

    public void setStart(boolean start) {
        this.start = start;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void stop() {
        this.start = false;
    }

}
