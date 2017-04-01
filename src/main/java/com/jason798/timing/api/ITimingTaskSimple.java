package com.jason798.timing.api;

/**
 * simple task for delay,fix rate
 * @author JasonLiu
 */
public interface ITimingTaskSimple {
    /**
     * unique tid
     * @return
     */
    String getTid();
    /**
     * business
     */
    void execute();
}
