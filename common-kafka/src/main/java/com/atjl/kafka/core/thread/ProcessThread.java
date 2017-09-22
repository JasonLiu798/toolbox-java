package com.atjl.kafka.core.thread;

import com.atjl.kafka.api.event.BatchEvent;
import com.atjl.kafka.consumer.TopicHandler;
import com.atjl.kafka.core.KafkaStaticContext;
import com.atjl.util.common.CheckUtil;
import com.atjl.util.queue.IQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 处理线程
 *
 *
 * @since 1.0
 */
public class ProcessThread extends BaseThread implements Runnable {
    private static Logger logger = LoggerFactory.getLogger(ProcessThread.class);

    protected IQueue dataQueue;
    //private IQueue commitQueue;
    protected TopicHandler handler;

    public ProcessThread(int id, IQueue dataQueue, TopicHandler handler) {
        CheckUtil.checkExistNull(dataQueue);
        CheckUtil.checkExistNull(handler);

        this.dataQueue = dataQueue;
        this.handler = handler;
        this.start = true;
    }

    /**
     * recv data from queue
     *
     * @return
     */
    protected BatchEvent recvData() {
        BatchEvent brd = null;
        try {
            // block on the queue
            brd = (BatchEvent) dataQueue.receiveMessage();
        } catch (InterruptedException e) {
            logger.error("recv data from DataQueue or cast exception");
            e.printStackTrace();
        }

//        if(KafkaStaticContext.isStatistics()) {
//            KafkaStaticContext.incrProcessGetDataTotalCount();
//        }
        return brd;
    }

    @Override
    public void run() {
        while (start) {
            BatchEvent brd = recvData();
            if (brd == null) {
                continue;
            }

            try {
                handler.execute(brd.getAllEvents());
            } catch (Exception e) {
                //TODO: process data error
                logger.error("process data error,data {}", brd);
            }

            if (KafkaStaticContext.isStatisticsProcess()) {
                KafkaStaticContext.incrProcessedDataTotalCount();
            }
        }
    }

}
