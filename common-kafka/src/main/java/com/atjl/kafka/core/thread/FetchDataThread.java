package com.atjl.kafka.core.thread;

import com.atjl.util.common.CheckUtil;
import com.atjl.util.queue.IQueue;
import com.atjl.kafka.api.config.ConsumerConfig;
import com.atjl.kafka.api.event.BatchEvent;
import com.atjl.kafka.api.event.Event;
import com.atjl.kafka.core.KafkaStaticContext;
import com.atjl.kafka.domain.constant.KafkaInnerConstant;

import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.message.MessageAndMetadata;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 自动提交offset
 * @since 1.0
 */
public class FetchDataThread extends BaseThread implements Runnable {
    private static final Logger LOG = LoggerFactory.getLogger(FetchDataThread.class);

    /**
     * 发送目标队列
     */
    protected IQueue dataQueue;

    /**
     * 抓取数据统计,线程各自统计，减少并发
     */
    protected int fetchCount =0;

    /**
     * kafka stream
     */
    protected KafkaStream<String, String> stream;

    /**
     * 定时发送 开关，初始化为false，true，则发送
     */
    public static boolean time2Process;

    /**
     * mq config
     */
    private ConsumerConfig mqconfig;

    //public FetchDataThread(int id, ConsumerConnector connector, KafkaStream<String, String> stream, IQueue dataQueue) {
    public FetchDataThread(int id, ConsumerConfig mqconfig, KafkaStream<String, String> stream, IQueue dataQueue) {
        //CheckUtil.checkExistNull(connector);
        CheckUtil.checkExistNull(stream);
        CheckUtil.checkExistNull(dataQueue);

        this.stream = stream;
        //this.consumer = connector;
        this.dataQueue = dataQueue;

//        time2Process = false;
    }

    /**
     * 初步格式化
     * @param msg
     * @return
     */
    protected Event rawData2Event(MessageAndMetadata<String,String> msg){

        String topic = msg.topic();
        //msg.message();
        int partition = msg.partition();
        long offset = msg.offset();

        //msgdate.topic();

        //value data
        String key = null;
        if (msg.key() != null) {
            key = msg.key();
        }
        String value = null;
        if (msg.message() != null) {
            value = msg.message();
        }
        //public Event(String topic, int partition, long offset, String key, String value)
        Event e = new Event(topic, partition, offset, key, value);
        return e;
    }

    protected void sendData(BatchEvent batchRawData){
        if(LOG.isDebugEnabled()){
            LOG.debug("batch raw data {}",batchRawData);
        }
        try {
            // put data to data queue
            this.dataQueue.sendMessage(batchRawData);
        } catch (InterruptedException e) {
            //lock interrupt,await interrupt
            LOG.error("send message iterrupt exception,data {}", batchRawData);
            e.printStackTrace();
        }
    }

    public void run() {
        if (LOG.isInfoEnabled()) {
            LOG.info("consumer {} resume running ", Thread.currentThread().getId());
        }

        ConsumerIterator<String, String> it = stream.iterator();
        BatchEvent batchRawData = null;

        while (it.hasNext()) {
            //get data
            MessageAndMetadata<String, String> msgdata = it.next();
            int pattition = msgdata.partition();

            //统计
            if(KafkaStaticContext.isStatisticsFetch()) {
                fetchCount++;
                //KafkaStaticContext.incrFetchDataTotalCount();
            }

            //格式化
            Event e = rawData2Event(msgdata);

            if (LOG.isDebugEnabled()) {
                //LOG.debug("thread {},receive raw data partition {},{}", this.id, patition, rd);
                LOG.debug("p {},o {},v {}",pattition , e.getOffset(),e.getValue());
            }

            if (batchRawData == null) {
                batchRawData = new BatchEvent();
            }
            batchRawData.addEvent2Partition(pattition, e);

            if (batchRawData.getTotalCount() < KafkaInnerConstant.BATCH_PROCESS_COUNT && !time2Process) {
                continue;
            } else {
                sendData(batchRawData);
                batchRawData = new BatchEvent();//批量发送完毕，重新生成新BatchRawData
            }
        }
        if(LOG.isDebugEnabled()) {
            LOG.debug("data recv thread id:{} end ", Thread.currentThread().getId());
        }
    }


    public int getFetchCount() {
        return fetchCount;
    }

    @Override
    public String toString() {
        return "FetchDataThread{" +
                "dataQueue=" + dataQueue +
                ", fetchCount=" + fetchCount +
                ", stream=" + stream +
                ", time2Process=" + time2Process +
                ", mqconfig=" + mqconfig +
                '}';
    }


}
