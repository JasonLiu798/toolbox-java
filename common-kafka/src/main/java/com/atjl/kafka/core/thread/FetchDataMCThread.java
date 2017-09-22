package com.atjl.kafka.core.thread;

import com.atjl.util.queue.IQueue;
import com.atjl.kafka.api.config.ConsumerConfig;
import com.atjl.kafka.api.event.BatchEventMC;
import com.atjl.kafka.api.event.Event;
import com.atjl.kafka.core.KafkaConsumeContext;
import com.atjl.kafka.core.KafkaStaticContext;
import com.atjl.kafka.domain.constant.KafkaInnerConstant;

import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.message.MessageAndMetadata;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 拉取数据，手动提交线程
 *
 * @since 1.0
 */
@SuppressWarnings("rawtypes")
public class FetchDataMCThread extends FetchDataThread implements Runnable {
    private static Logger LOG = LoggerFactory.getLogger(FetchDataMCThread.class);
    /**
     * commit status context
     */
    private KafkaConsumeContext context;


	public FetchDataMCThread(int id, ConsumerConfig config, KafkaConsumeContext context, KafkaStream<String, String> stream, IQueue dataQueue) {
        super(id,config,stream,dataQueue);
        this.context = context;
    }

    @Override
    public void run() {
        if (LOG.isDebugEnabled()) {
            LOG.debug("consumer {} resume running ", Thread.currentThread().getId());
        }

        ConsumerIterator<String, String> it = stream.iterator();
        BatchEventMC batchRawData = null;

        while (it.hasNext()) {
            //get data
            MessageAndMetadata<String, String> msgdata = it.next();
//            if (LOG.isDebugEnabled()) {
//                LOG.debug("get raw data {}", msgdate);
//            }
            //统计
            if(KafkaStaticContext.isStatisticsFetch()) {
                fetchCount++;
                //KafkaStaticContext.incrFetchDataTotalCount();
            }

            //data check and process
            //meta data
            int patition = msgdata.partition();
            Event rd = rawData2Event(msgdata);

            if (LOG.isDebugEnabled()) {
                //LOG.debug("thread {},receive raw data partition {},{}", this.id, patition, rd);
                LOG.debug("p{},o {},v {}", patition, rd.getOffset(),rd.getValue());
            }


            if (batchRawData == null) {
                batchRawData = new BatchEventMC();//初始化
            }
            batchRawData.addEvent2Partition(patition, rd);

            if (batchRawData.getTotalCount() < KafkaInnerConstant.BATCH_PROCESS_COUNT && !time2Process) {
                continue;
            } else {
                //contex 记录未处理数据
                batchRawData.setOffsetMap(context.addUnProcessData(batchRawData));
                sendData(batchRawData);
                batchRawData = new BatchEventMC();
            }
        }
        if(LOG.isDebugEnabled()) {
            LOG.debug("data recv thread id:{} end ", Thread.currentThread().getId());
        }
    }


    @Override
    public String toString() {
        return "FetchDataMCThread{" +
                "context=" + context +
                '}';
    }
}
