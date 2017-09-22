package com.atjl.kafka.consumer;

import com.atjl.kafka.core.KafkaThreadContext;
import com.atjl.kafka.core.thread.TimingStatisticsThread;
import com.atjl.util.common.CheckUtil;
import com.atjl.util.queue.QueueManager;
import com.atjl.kafka.api.config.ConsumerConfig;
import com.atjl.kafka.api.exception.MQException;
import com.atjl.kafka.core.KafkaStaticContext;
import com.atjl.kafka.core.thread.FetchDataThread;
import com.atjl.kafka.core.thread.ProcessThread;
import com.atjl.kafka.domain.constant.KafkaInnerConstant;

import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;
import kafka.serializer.StringDecoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.*;

/**
 * kafka consumer 定时自动提交
 *
 * @since 1.0
 */
public class Consumer {

    private static Logger LOG = LoggerFactory.getLogger(Consumer.class);
    /**
     * configs
     */
    protected ConsumerConfig config;
    protected String propertiesFile;

    /**
     * kafak zookeeper connector
     */
    protected ConsumerConnector consumer;

    /**
     * kafka consumer stream
     */
    protected List<KafkaStream<String, String>> streams;

    /**
     * fetch data thread pool
     */
    protected ExecutorService fetchDataPool;

    /**
     * data process thread pool
     */
    protected ExecutorService processDataPool;

    /**
     * commitPartition thread
     * batch recv switch thread
     */
    protected ScheduledExecutorService timerProcessPool;

    /**
     * consumer status init->running->stopping->stopped
     */
    protected Status status;


    public enum Status {
        INIT, SUBSCRIBED, RUNNING, STOPPING, STOPPED;
    }

    public Consumer() {
        this.config = new ConsumerConfig();
        if (LOG.isDebugEnabled()) {
            LOG.debug("mqconfig {}", config);
        }
        Properties properties = this.config.createConsumerConfig();
        kafka.consumer.ConsumerConfig config = new kafka.consumer.ConsumerConfig(properties);

        consumer = kafka.consumer.Consumer.createJavaConsumerConnector(config);

        if (LOG.isDebugEnabled()) {
            LOG.debug("construct mq config {}", this.config);
        }
        changeStatusAndLog(Status.INIT);
    }

    /**
     * @param topic
     */
    public void subscribeTopic(String topic, TopicHandler handler) {
        CheckUtil.checkStrExistNull(topic);
        CheckUtil.checkExistNull(handler);
        this.config.setTopic(topic);
        this.config.setTopicHandler(handler);
        changeStatusAndLog(Status.SUBSCRIBED);
    }

    /**
     * 定义
     * @param topics
    public void subscribeTopics(List<String> topics){
    for(String topic:topics) {
    initSubscribeTopic(topic);
    }
    }*/


    /**
     * construct kafka stream
     */
    public void constructStream() {
//        CheckUtil.checkExistNull(this.config.getTopic());
        if (LOG.isDebugEnabled()) {
            LOG.debug("construct kafka stream of topic {}", this.config.getTopic());
        }
        //init topic map
        Map<String, Integer> topicCountMap = new HashMap<>();
        //for(String topic:this.config.getTopic()) {
        topicCountMap.put(this.config.getTopic(), this.config.getFetchThreadPoolSize());
        //}

        //init consumer map
        Map<String, List<KafkaStream<String, String>>> consumerMap = consumer.createMessageStreams(topicCountMap, new StringDecoder(null), new StringDecoder(null));

        //get stream
        this.streams = consumerMap.get(this.config.getTopic());

        if (LOG.isDebugEnabled()) {
            LOG.debug("stream count {}", this.streams.size());
        }
    }

    /**
     * init thread pool
     *
     * @param timerThreadCount timer thread count
     */
    public void initPool(int timerThreadCount) {
        fetchDataPool = Executors.newFixedThreadPool(this.config.getFetchThreadPoolSize());
        processDataPool = Executors.newFixedThreadPool(this.config.getProcessThreadPoolSize());
        timerProcessPool = Executors.newScheduledThreadPool(timerThreadCount);
    }

    /**
     * resume thread
     */
    public void start() {
        if (this.status != Status.SUBSCRIBED) {
            throw new MQException("consumer has not subscribe topics");
        }
        constructStream();
        initPool(this.config.getTimingThreadPoolSize());

        int timingId = 1;
        startFetchThread();
        startProcessThread();
        startStatThread(timingId);
        changeStatusAndLog(Status.RUNNING);
    }

    protected void changeStatusAndLog(Status st) {
        this.status = st;
        if (LOG.isInfoEnabled()) {
            LOG.info("consumer status {}", st);
        }
    }

    private void startFetchThread() {
        int threadNumber = 0;
        for (final KafkaStream<String, String> stream : streams) {
            FetchDataThread fetchDataThread = new FetchDataThread(threadNumber, this.config, stream, QueueManager.getQueue(KafkaInnerConstant.DATA_QUEUE_KEY));
            fetchDataPool.submit(fetchDataThread);

            threadNumber++;
            KafkaThreadContext.addThread(KafkaThreadContext.FETCH_THREAD, fetchDataThread);
        }
    }

    private void startProcessThread() {
        int processThreadCount = this.config.getProcessThreadPoolSize();
        int threadNumber = 0;
        for (int i = 0; i < processThreadCount; i++) {
            ProcessThread processThread = new ProcessThread(threadNumber, QueueManager.getQueue(KafkaInnerConstant.DATA_QUEUE_KEY), this.config.getTopicHandler());
            fetchDataPool.submit(processThread);
            threadNumber++;
            KafkaThreadContext.addThread(KafkaThreadContext.PROCESS_THREAD, processThread);
        }
    }

    protected void startStatThread(int id) {
        TimingStatisticsThread tst = new TimingStatisticsThread(id, KafkaStaticContext.getStatisticsIntervalMillSecond());
        timerProcessPool.scheduleAtFixedRate(tst,
                0,
                tst.getDelay(),
                tst.getTimeUnit());

        if (LOG.isDebugEnabled()) {
            LOG.debug("resume thread statistics,interval {}", tst.getInterval());
        }
    }


    /**
     * shutdown manager
     */
    public void shutdown() {
        changeStatusAndLog(Status.STOPPING);

        if (consumer != null) {
            consumer.shutdown();
        }
        stopPool(fetchDataPool);
        stopPool(processDataPool);
        stopPool(timerProcessPool);
        changeStatusAndLog(Status.STOPPED);
    }

    protected void stopPool(ExecutorService es) {
        if (es != null) {
            es.shutdown();
        } else {
            return;
        }
        try {
            if (!es.awaitTermination(5000, TimeUnit.MILLISECONDS)) {
                LOG.info("Timed out waiting for consumer threads to shut down, exiting uncleanly");
            }
        } catch (InterruptedException e) {
            LOG.error("Interrupted during shutdown, exiting uncleanly");
        }
    }

    /**
     * --------------------- getters && setters ---------------------------
     */


    public List<KafkaStream<String, String>> getStreams() {
        return streams;
    }

    public ConsumerConnector getConsumer() {
        return consumer;
    }

    public void setConsumer(ConsumerConnector consumer) {
        this.consumer = consumer;
    }

    public void setStreams(List<KafkaStream<String, String>> streams) {
        this.streams = streams;
    }

    public ExecutorService getFetchDataPool() {
        return fetchDataPool;
    }

    public void setFetchDataPool(ExecutorService fetchDataPool) {
        this.fetchDataPool = fetchDataPool;
    }

    public ExecutorService getProcessDataPool() {
        return processDataPool;
    }

    public void setProcessDataPool(ExecutorService processDataPool) {
        this.processDataPool = processDataPool;
    }

    public ScheduledExecutorService getTimerProcessPool() {
        return timerProcessPool;
    }

    public void setTimerProcessPool(ScheduledExecutorService timerProcessPool) {
        this.timerProcessPool = timerProcessPool;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public ConsumerConfig getConfig() {
        return config;
    }

    public void setConfig(ConsumerConfig config) {
        this.config = config;
    }
}
