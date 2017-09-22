package com.atjl.kafka.core.thread;

import com.atjl.kafka.core.KafkaThreadContext;
import com.atjl.util.collection.CollectionUtil;
import com.atjl.util.common.CheckUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.List;

/**
 * TimingBatchEventForceSendThread
 *
 * @since 1.0
 */
public class TimingBatchEventForceSendThread extends TimingBaseThread implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(TimingBatchEventForceSendThread.class);
    List<FetchDataThread> fetchThreadList;
    @SuppressWarnings("unused")
    private boolean initFail = true;

    public TimingBatchEventForceSendThread(int id, int interval) {
        super(id, interval);
        List<BaseThread> list = KafkaThreadContext.getThreadList(KafkaThreadContext.FETCH_THREAD);
        CheckUtil.checkExistNull(list);

        if (CollectionUtil.isEmpty(list)) {
            logger.error("get null fetch thread from KafkaThreadContext");
        } else {
            this.fetchThreadList = new LinkedList<>();
            for (BaseThread t : list) {
                FetchDataThread ft = (FetchDataThread) t;
                this.fetchThreadList.add(ft);
            }
            initFail = false;
            if (logger.isDebugEnabled()) {
                logger.debug("init TimingBatchEventForceSendThread success,list {}", this.fetchThreadList);
            }
        }
    }


    @Override
    public void run() {
//        if(initFail){
//            return;
//        }
//        for(@SuppressWarnings("unused") FetchDataThread t:fetchThreadList){
//            //t.time2Process();
//        }
    }

}
