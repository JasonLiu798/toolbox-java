package com.jason798.timing.entity;

/**
 * task type
 *
 * @author JasonLiu
 */
public enum TaskEnum {
    /**
     * delay execute once
     */
    DELAY,
    /**
     * execute run at fix rate
     */
    FIXRATE,
    /**
     * execute run at fix rate until condition reach
     */
    FIXRATECOND
}
