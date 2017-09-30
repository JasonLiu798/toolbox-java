package com.atjl.dbtiming.api;

/**
 * condition task
 *
 * @author JasonLiu
 */
public interface ICond {
    /**
     * return true,task stop
     */
    boolean cond(String param);
}
