package com.atjl.dbtiming.task;

import com.atjl.dbtiming.api.ITaskExecute;

/**
 * counter reset task
 */
public class CounterResetTask implements ITaskExecute {
    //private static Logger LOG = LoggerFactory.getLogger(CounterResetTask.class);

    @Override
    public void execute() {
        //kafka
//        long kafkaSendCnt = ErrorContext.KAFKA_SEND_CNT.longValue();
//        if( kafkaSendCnt> 10) {
//            ErrorContext.KAFKA_SEND_CNT.set(kafkaSendCnt / 2);
//            LogDbUtil.warn(TimingConstant.MODULE_TIMING,"decrease kafkaSendCnt");
//        }

        //log error
//        long logWriteError = LogConstant.WRITE_ERROR.longValue();
//        if(logWriteError > 10) {
//            LogConstant.WRITE_ERROR.set(logWriteError / 2);
//            LogDbUtil.warn(TimingConstant.MODULE_TIMING,"decrease logWriteErrorCnt");
//        }
    }

}
