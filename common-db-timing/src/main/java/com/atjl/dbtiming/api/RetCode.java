package com.atjl.dbtiming.api;

/**
 * return code
 * @author JasonLiu
 */
public enum RetCode {
    /**
     * execute succ
     */
    SUCCESS,

    TID_NULL,
    TID_POOL_EXIST,

    SERVICE_NULL,
    COND_METHOD_NULL,

    HAS_LOCK,

    UNKNOWN,

    ADD_CRON_POOL_FAIL,
    ADD_CRON_POOL_EXCEPTION,

    STILL_ALIVE,

    MAX_CNT_ZERO,

    RE_ADD_DYN_TASK_TO_POOL_FAIL,
    RE_ADD_DYN_TASK_TO_POOL_EXCEPTION,

    ADD_CRON_TO_POOL_FAIL,
    ADD_CRON_TO_POOL_EXCEPTION,

    /**
     * state
     */
    NOT_RUNNIG,
    NOT_STOPPED,

    /**
     * parameter error
     */
    DELAY_NULL,
    DELAY_BELOW_ZERO,
    INTERVAL_NULL,
    INTERVAL_BELOW_ZERO,



    /**
     * db
     */
    ADD_DYN_TASK_DB_FAIL,
    ADD_CRON_DB_FAIL,
    RE_CHECK_ADD_DYN_TASK_DB_FAIL,

    UPD_TASK_STATUS_DB_FAIL,

    CONCURRENT_DB_INSERT_NOT_MIN,

    TASK_NOT_EXIST_IN_DB,



    /**
     * lock
     */
    GET_LOCK_FAIL,

    /**
     * manual run exception
     */
    MANUAL_RUN_REFLECT_EXCEPTION,

    /**
     * status associate
     */
    STATUS_CANNOT_START,

    DB_TASK_NULL,
    TASK_PARAM_NULL,

    /**
     * bean associate
     */
    BEAN_NOT_EXIST,
    GET_BEAN_FAIL,
    GET_BEAN_NULL,

    //run cnt reach max
MAX_CNT_NULL,
    RUN_CNT_REACH_MAX_CNT,

    HAS_PARAM_NOT_IMPLEMENT_INF,
    NO_PARAM_NOT_IMPLEMENT_INF,
    HAS_COND_NOT_IMPLEMENT_INF,

    //got cond,must has param, ICond cond(String xxx)
    HAS_COND_BUT_NOT_HAS_PARAM,

    /**
     * init schedule pool fail
     */
    INIT_POOL_FAIL,
    CRON_EXPESSION_INVALID,
    UPD_CRON_RESTART_FAIL,
    TASK_NOT_IN_THE_POOL,
    CANCEL_TASK_FAIL,

   TASK_STOP_OR_INVALID

}
