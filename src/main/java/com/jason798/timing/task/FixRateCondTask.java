package com.jason798.timing.task;


import com.jason798.log.LogClient;
import com.jason798.timing.TimingContext;
import com.jason798.timing.api.ITimingTaskCond;
import com.jason798.timing.entity.TaskEnum;

/**
 * fix rate until reach the condition
 *
 * @author JasonLiu
 */
public class FixRateCondTask extends FixRateTask {
    /**
     * runed counter
     */
    protected int runedCnt = 0;

    public FixRateCondTask(String tid, ITimingTaskCond service) {
        super(tid);
        this.type = TaskEnum.FIXRATECOND;
        this.service = service;
    }

    /**
     * buziness obj
     */
    private ITimingTaskCond service;

    @Override
    public void before() {
        super.before();
    }

    @Override
    public void run() {
        before();
        try {
            service.execute();
        } catch (Exception e) {
            LogClient.writeError(FixRateCondTask.class,"timing task run exception", e);
        }
        after();
    }

    /**
     * check condition reach
     */
    @Override
    public void after() {
        runedCnt++;
        super.after();
        if(service.cond()){
            removeStatus();
            TimingContext.cancleTask(tid);
        }
    }

    /**
     * ################## getter & setter ################################
     */
    public int getRunedCnt() {
        return runedCnt;
    }

    public void setRunedCnt(int runedCnt) {
        this.runedCnt = runedCnt;
    }

    public ITimingTaskCond getService() {
        return service;
    }

    public void setService(ITimingTaskCond service) {
        this.service = service;
    }
}
