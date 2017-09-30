package com.atjl.dbtiming.domain.biz;

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
     * cron execute
     */
    CRON,
    /**
     * execute run at fix rate
     */
    FIXRATE,


    /**
     * execute run at fix rate,run N times
     */
    FIXRATECNT,
//    FIXRATECNTPARAM,

    /**
     * execute run at fix rate until condition reach
     */
    FIXRATECNTCOND,
//    FIXRATECNTCONDPARAM
}
