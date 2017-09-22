package com.atjl.kafka.core.thread;

/**
 * BaseThread
 * 基础线程类，提供ID，运行状态设置
 *
 * @since 1.0
 */
public abstract class BaseThread {
    protected boolean start;

    public void stop() {
        this.start = false;
    }
}
