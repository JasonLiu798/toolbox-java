package com.jason798.timing.task;

import sf.aos.common.LogClient;
import sf.aos.timing.api.ITimingTaskSimple;
import sf.aos.timing.entity.TaskEnum;

/**
 * delay execute task
 *
 * @author JasonLiu
 */
public class DelayTask extends BaseTask {

    private ITimingTaskSimple service;

    protected boolean runned = false;

    public DelayTask(String tid, ITimingTaskSimple service) {
        super(tid);
        this.type = TaskEnum.DELAY;
        this.service = service;
    }

    @Override
    public void before() {
        super.before();
    }

    @Override
    public void run() {
        before();
        try {
            service.execute();
        }catch (Exception e){
            LogClient.writeError(DelayTask.class,"delay task execute error",e);
        }
        after();
        runned = true;
    }

    @Override
    public void after(){
        super.after();
        removeStatus();
    }


    /**
     * ############## getter & setter #################
     */
    public void setService(ITimingTaskSimple service) {
        this.service = service;
    }
    public ITimingTaskSimple getService() {
        return service;
    }

    public boolean isRunned() {
        return runned;
    }

    public void setRunned(boolean runned) {
        this.runned = runned;
    }
}
