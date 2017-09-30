package com.atjl.dbtiming.task;

import com.atjl.dbtiming.helper.TimingInnerManager;
import com.atjl.dbtiming.domain.biz.TaskEnum;

import java.lang.reflect.Method;

/**
 * fix rate execute task
 *
 * @author JasonLiu
 */
public class FixRateTask extends BaseTimingTask {
    /**
     * constructor
     *
     * @param tid
     * @param helper
     * @param target
     * @param method
     */
    public FixRateTask(Long tid, TimingInnerManager helper, Object target, Method method) {
        super(tid, helper, target, method);
        this.type = TaskEnum.FIXRATE;
    }

}
