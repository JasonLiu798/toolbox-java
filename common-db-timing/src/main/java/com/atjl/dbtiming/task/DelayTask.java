package com.atjl.dbtiming.task;

import com.atjl.dbtiming.helper.TimingInnerManager;

import java.lang.reflect.Method;

/**
 * delay execute task
 *
 * @author JasonLiu
 */
public class DelayTask extends TimingTaskBase {

    protected boolean runned = false;

    /**
     * constructor
     *
     * @param tid
     * @param helper
     * @param target
     * @param method
     */
    public DelayTask(Long tid, TimingInnerManager helper, Object target, Method method) {
        super(tid, helper, target, method);
    }


//    public DelayTask(Long tid, TimingInnerManager helper, ITaskExecute service) {
//        super(tid,helper);
//        this.type = TaskEnum.DELAY;
//    }

    @Override
    public void after(){
        runned = true;
        end = true;
        super.after();
    }


    /**
     * ############## getter & setter #################
     */
    public boolean isRunned() {
        return runned;
    }

    public void setRunned(boolean runned) {
        this.runned = runned;
    }
}
