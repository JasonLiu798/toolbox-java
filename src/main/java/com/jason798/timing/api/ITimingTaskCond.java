package com.jason798.timing.api;

/**
 * condition task
 *
 * @author JasonLiu
 */
public interface ITimingTaskCond extends ITimingTaskSimple {
    /**
     * is reach the condition
     */
    boolean cond();
}
