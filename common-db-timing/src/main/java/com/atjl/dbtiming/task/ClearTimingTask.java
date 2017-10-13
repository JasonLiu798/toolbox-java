package com.atjl.dbtiming.task;

import com.atjl.dbtiming.helper.TimingInnerManager;
import com.atjl.dbtiming.api.ITaskExecute;

/**
 * clear ts_task 's end task and task's history
 * insert into ts_task_runed
 */
public class ClearTimingTask implements ITaskExecute {

    /**
     * need manual set
     */
    private TimingInnerManager timingInnerManager;

    @Override
    public void execute() {

        timingInnerManager.clearTask();

    }

    public TimingInnerManager getTimingInnerManager() {
        return timingInnerManager;
    }

    public void setTimingInnerManager(TimingInnerManager timingInnerManager) {
        this.timingInnerManager = timingInnerManager;
    }
}
