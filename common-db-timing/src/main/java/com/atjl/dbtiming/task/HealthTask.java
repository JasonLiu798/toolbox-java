package com.atjl.dbtiming.task;

import com.atjl.util.common.SystemUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.atjl.dbtiming.helper.TimingInnerManager;
import com.atjl.dbtiming.api.ITimingTask;
import com.atjl.dbtiming.domain.biz.TimingConstant;

/**
 * update alive time
 */
public class HealthTask implements ITimingTask {
    private static Logger LOG = LoggerFactory.getLogger(HealthTask.class);

    /**
     * need manual set
     */
    private TimingInnerManager timingInnerManager;

    @Override
    public void execute() {
        //sleep random time,0~3s,avoid other thread
        SystemUtil.sleepRandom(TimingConstant.SLEEP_RAND_MAX_TM, TimingConstant.SLEEP_DFT_TM);
        if(LOG.isDebugEnabled()) {
            LOG.debug(HealthTask.class+" start to run");
        }
        //find dead not execute task and re execute
        //todo:remove task execute over cnount
        timingInnerManager.reExecTask();
        //update live tm
        timingInnerManager.updateLiveTm();
        //update manager live tm
        timingInnerManager.updateManagerLiveTm();
    }


    /**
     * ############### getter & setter ##################
     */
    public TimingInnerManager getTimingInnerManager() {
        return timingInnerManager;
    }

    public void setTimingInnerManager(TimingInnerManager timingInnerManager) {
        this.timingInnerManager = timingInnerManager;
    }
}
