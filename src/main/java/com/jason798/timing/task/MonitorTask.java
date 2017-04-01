package com.jason798.timing.task;


import com.jason798.timing.TimingContext;
import com.jason798.timing.TimingCoreHelper;
import com.jason798.timing.api.ITimingTaskSimple;
import com.jason798.timing.entity.TimingConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * monitor thread
 *
 * @author JasonLiu
 */
public class MonitorTask implements ITimingTaskSimple {

    private static Logger LOG = LoggerFactory.getLogger(TimingCoreHelper.class);

    private static long rate = 1000;
    private static long delay = 1000;

    @Override
    public String getTid() {
        return TimingConstant.MONITOR_THREAD_ID;
    }

    @Override
    public void execute() {
        String status = TimingContext.getStatusFmt();
        LOG.debug("timing status:" + status);
    }
}
