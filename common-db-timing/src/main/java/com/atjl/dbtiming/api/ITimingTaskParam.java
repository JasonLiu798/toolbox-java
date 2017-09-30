package com.atjl.dbtiming.api;

/**
 * simple task for delay,fix rate
 *
 * @author JasonLiu
 */
public interface ITimingTaskParam {
    /**
     * business
     */
    void execute(String param);
}
