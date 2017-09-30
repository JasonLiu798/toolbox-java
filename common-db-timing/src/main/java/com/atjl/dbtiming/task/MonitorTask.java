package com.atjl.dbtiming.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.atjl.dbtiming.domain.TimingContext;
import com.atjl.dbtiming.helper.TimingInnerManager;
import com.atjl.dbtiming.api.ITimingTask;

/**
 * monitor thread
 *
 * @author JasonLiu
 */
public class MonitorTask implements ITimingTask {
    private static Logger LOG = LoggerFactory.getLogger(TimingInnerManager.class);

    @Override
    public void execute() {
        String status = TimingContext.getStatusFmt();
        LOG.debug("timing status:" + status);
    }

}
