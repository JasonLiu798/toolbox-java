package com.atjl.dbtiming.api;

import com.atjl.dbtiming.api.domain.RetCode;
import com.atjl.dbtiming.domain.biz.TaskDomain;
import com.atjl.dbtiming.domain.biz.TimingManagerStatusDto;
import com.atjl.dbtiming.domain.gen.GenTask;

import java.util.List;

/**
 * timing manager
 *
 * @author JasonLiu
 */
public interface TimingService {
    /**
     * init
     */
    void init();

    /**
     * task with specified stop condition,and max run time,with param
     *
     * @return
     *
    RetCode addDynamicTask(AddTaskParam param);

    /**
     * dynamic add cron task
     *
     * @return
     *
    RetCode addCronTask(AddTaskParam param);*/

    /**
     * @param tid
     * @param cronExpression
     * @return
     */
    RetCode updateCronTask(Long tid, String cronExpression);

    /**
     * exec cron task
     *
     * @param tid
     */
    RetCode execCronTask(Long tid);

    /**
     * re exec dynamic task
     *
     * @param tid
     */
    RetCode reExecDynamicTask(Long tid);

    /**
     * get unique id
     *
     * @return
     */
    String getManagerId();

    /**
     * manual run
     *
     * @param tid
     * @param param
     * @return
     */
    RetCode manualRun(Long tid, String param);

    List<GenTask> manualClearTask();

    /**
     * #################### db operation ######################
     */
    List<TaskDomain> getTaskList(String valid, String showEnd);

    /**
     * #################### sys operations ####################
     */
    /**
     * @return
     */
    TimingManagerStatusDto getStatus();

    /**
     * manual stop pool
     */
    RetCode manualStopAll();

    /**
     * manual stop task
     *
     * @param tid
     * @return
     */
    RetCode manualStop(Long tid, String type);

    /**
     * manual start pool
     */
    RetCode manualStart();

    /**
     * restart pool
     */
    RetCode restart();


}
