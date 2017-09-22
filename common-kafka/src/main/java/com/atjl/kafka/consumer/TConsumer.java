package com.atjl.kafka.consumer;

import com.atjl.util.common.CheckUtil;
import com.atjl.util.queue.QueueManager;
import com.atjl.kafka.core.KafkaConsumeContext;
import com.atjl.kafka.core.thread.FetchDataMCThread;
import com.atjl.kafka.core.thread.ProcessMCThread;
import com.atjl.kafka.core.thread.TimingBatchEventForceSendThread;
import com.atjl.kafka.core.thread.TimingCommitThread;
import com.atjl.kafka.domain.constant.KafkaConfigConstant;
import com.atjl.kafka.domain.constant.KafkaInnerConstant;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Properties;

/**
 * Kafka consumer 手动提交
 *
 * use high level consumer API
 * for version <= 0.8.2
 * <p>
 * ZookeeperConsumerConnector
 * Consumer:
 * /consumers/[group_id]/ids[consumer_id] -> topic1,...topicN
 * <p>
 * Consumer Offset Value:
 * /consumers/[group_id]/offsets/[topic]/[broker_id-partition_id] --> offset_counter_value
 * <p>
 * if you provide more threads than there are partitions on the topic, some threads will never see a message
 * if you have more partitions than you have threads, some threads will receive data from multiple partitions
 * if you have multiple partitions per thread there is NO guarantee about the order you receive messages, other than that within the partition the offsets will be sequential.
 * For example, you may receive 5 messages from partition 10 and 6 from partition 11, then 5 more from partition 10 followed by 5 more from partition 10 even if partition 11 has data available.
 * adding more processes/threads will cause Kafka to re-balance, possibly changing the assignment of a Partition to a Thread.
 *
 * @since 1.0
 */
public class TConsumer extends Consumer {

    private static Logger LOG = LoggerFactory.getLogger(TConsumer.class);

    /**
     * configs
     */
    private KafkaConsumeContext context;

    public TConsumer() {
        this.propertiesFile = KafkaConfigConstant.DFT_CONF_FILE;
    }

    public TConsumer(String propertiesFile) {
        this.propertiesFile = propertiesFile;
    }

    public void initMethod() {
        //properties file or spring inject
        CheckUtil.checkAllNull(this.propertiesFile, this.config);

        //generate config
//        this.config = ConfigModelUtil.generateConfigModel(ConsumerConfig.class,KafkaConfigConstant.CCONF_PREFIX, KafkaConfigConstant.DFT_CONF_FILE, null);
        CheckUtil.checkExistNull(this.config);

        Properties properties = this.config.createConsumerConfig();
        kafka.consumer.ConsumerConfig config = new kafka.consumer.ConsumerConfig(properties);

        consumer = kafka.consumer.Consumer.createJavaConsumerConnector(config);
        if (LOG.isDebugEnabled()) {
            LOG.debug("construct mq config {}", this.config);
        }
        context = new KafkaConsumeContext();

        status = Consumer.Status.INIT;
    }


    /**
     * 启动consumer
     */
    public void start() {
        constructStream();
        initPool(this.config.getTimingThreadPoolSize() + 1);

        /**
         * resume threads
         */
        int timingId = 0;
        startFetchThread();
        startFetchTimngBatchEventForceSendThread(timingId++);
        startProcessThread();
        startCommitThread(timingId++);
        startStatThread(timingId++);

        this.status = Status.RUNNING;
        if (LOG.isInfoEnabled()) {
            LOG.info("resume running");
        }
    }

    /**
     * fetch data thread
     *
     * @return
     */
    private void startFetchThread() {
        int threadNumber = 0;
        for (final KafkaStream<String, String> stream : streams) {
            fetchDataPool.submit(new FetchDataMCThread(threadNumber, this.config, this.context, stream, QueueManager.getQueue(KafkaInnerConstant.DATA_QUEUE_KEY)));
            threadNumber++;
        }
        if (LOG.isDebugEnabled()) {
            LOG.debug("resume thread fetch count {}", threadNumber);
        }
    }

    /**
     * 启动定时强制发送数据
     */
    private void startFetchTimngBatchEventForceSendThread(int id) {
        TimingBatchEventForceSendThread t = new TimingBatchEventForceSendThread(id, this.config.getBatchEventForceSendInterval());

        timerProcessPool.scheduleAtFixedRate(
                t,
                t.getDelay(),
                t.getInterval(),
                t.getTimeUnit());
        if (LOG.isDebugEnabled()) {
            LOG.debug("resume thread force send,interval {}", t.getInterval());
        }
    }

    private void startProcessThread() {
        int processThreadCount = this.config.getProcessThreadPoolSize();
        int threadNumber = 0;
        for (int i = 0; i < processThreadCount; i++) {
            fetchDataPool.submit(new ProcessMCThread(threadNumber, this.context, QueueManager.getQueue(KafkaInnerConstant.DATA_QUEUE_KEY), this.config.getTopicHandler()));
            threadNumber++;
        }
        if (LOG.isDebugEnabled()) {
            LOG.debug("resume thread process count {}", processThreadCount);
        }
    }

    /**
     * resume commit thread
     */
    private void startCommitThread(int id) {
        TimingCommitThread tct = new TimingCommitThread(id,
                this.config.getCommitInterval(),
                this.config.getTopic(),
                this.getConsumer(),
                this.context);
        timerProcessPool.scheduleAtFixedRate(
                tct,
                tct.getDelay(),
                tct.getInterval(),
                tct.getTimeUnit());
        if (LOG.isDebugEnabled()) {
            LOG.debug("resume thread commit,interval {}", tct.getInterval());
        }
    }


    public ConsumerConnector getConsumer() {
        return consumer;
    }

    public void setConsumer(ConsumerConnector consumer) {
        this.consumer = consumer;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public KafkaConsumeContext getContext() {
        return context;
    }

    public List<KafkaStream<String, String>> getStreams() {
        return streams;
    }
}
