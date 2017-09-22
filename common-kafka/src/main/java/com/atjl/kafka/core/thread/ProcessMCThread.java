package com.atjl.kafka.core.thread;

import com.atjl.kafka.consumer.TopicHandler;
import com.atjl.util.queue.IQueue;
import com.atjl.kafka.api.event.BatchEventMC;
import com.atjl.kafka.core.KafkaConsumeContext;
import com.atjl.kafka.core.KafkaStaticContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 处理线程
 * 手动提交offset
 *
 * @since 1.0
 */
public class ProcessMCThread extends ProcessThread implements Runnable {
    private static Logger LOG = LoggerFactory.getLogger(ProcessMCThread.class);

    private KafkaConsumeContext context;

    public ProcessMCThread(int id, KafkaConsumeContext consumeContext, IQueue dataQueue, TopicHandler handler) {
        super(id, dataQueue, handler);
        this.context = consumeContext;
    }

    /**
     * @return
     */
    @Override
    protected BatchEventMC recvData() {
        BatchEventMC brd = null;
        try {
            // block on the queue
            brd = (BatchEventMC) dataQueue.receiveMessage();
        } catch (InterruptedException e) {
            LOG.error("recv data from DataQueue or cast exception");
            e.printStackTrace();
        }
        return brd;
    }

    @Override
    public void run() {
        while (start) {
            BatchEventMC brd = recvData();
            if (brd == null) {
                continue;
            }

            try {
                handler.execute(brd.getAllEvents());
                if (LOG.isDebugEnabled()) {
                    LOG.debug("PROCESS CTX: {} ", context.getContextStatus());
                }
            } catch (Exception e) {
                //TODO: process data error
                LOG.error("process data error,data {}", brd);
            }

            //batch add processed data
            context.addProcessedData(brd);

            if (KafkaStaticContext.isStatisticsProcess()) {
                KafkaStaticContext.incrProcessedDataTotalCount();
            }
//            try {
//                BatchCommitData bcd = new BatchCommitData();
//                commitQueue.sendMessage(cd);
//            } catch (InterruptedException e) {
//                LOG.error("send to commitQueue exception,data {}",rd);
//                e.printStackTrace();
//            }
        }
    }

}
