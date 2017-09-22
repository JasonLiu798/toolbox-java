package com.atjl.kafka.api.config;

import com.atjl.kafka.consumer.TopicHandler;
import com.atjl.util.common.CheckUtil;

import java.io.Serializable;
import java.util.*;


public class ConsumerConfig implements Serializable {
    private static final long serialVersionUID = 6633220195327111885L;

    /**
     * KAFKA zookeeper.connect
     */
    protected String zookeeper;
    /**
     * topics
     */
    protected String topic;//new HashMap<>();
    /**
     *
     */
    protected TopicHandler topicHandler;

    /**
     * KAFKA group.id
     */
    protected String groupId = "defaultGroup";

    /**
     * KAFKA consumer.id
     * auto generate
     */
    private String consumerId;
    /**
     * KAFKA client.id
     *
     *
     private String clientId = groupId;*/


    /**
     * ###################### poolsize area ###########################
     */
    /**
     * KAFKA num.consumer.fetchers
     * fetch data thread count
     */
    protected int fetchThreadPoolSize = 1;

    /**
     * process data thread count
     * = ([cpu time] + [cpu idle time])/[cpu time] * [cpu core count]
     */
    protected int processThreadPoolSize = Runtime.getRuntime().availableProcessors() * 2;

    /**
     * for stat,commit
     * statistics thread x1
     */
    protected int timingThreadPoolSize = 1 + 1;

    /**
     * ################## no-kafka internal config area #####################
     */
    /**
     * force send event to queue interval
     * unit :millsecond
     */
    protected int batchEventForceSendInterval = 3000;

    /**
     * 批量处理数量，大小根据实际处理情况
     * 推荐配置：
     * 根据消费者的处理能力 ，消息量大小 确定
     */
    protected int batchMessageCount = 5;


    /**
     * ################## kafka internal config area #####################
     */
    /**
     * ################## fetch config area #####################
     */
    /**
     * KAFKA fetch.min.bytes
     * depend on sender message count,message size,consumer process ability
     */
    protected int fetchMinBytes = 1;
    /**
     * KAFKA fetch.wait.max.ms
     * The maximum amount of time the server will block before answering the fetch request
     * if there isn't sufficient data to immediately satisfy fetch.min.bytes
     */
    private int fetchWaitMax = 100;


    /**
     * KAFKA socket.receive.buffer.bytes
     */
    private long recvBufferSizeBytes = 64 * 1024;

    /**
     * KAFKA fetch.message.max.bytes
     */
    private long fetchMaxBytes = 1024 * 1024;

    /**
     * KAFKA queued.max.message.chunks
     * Max number of message chunks buffered for consumption.
     * Each chunk can be up to fetch.message.max.bytes.
     */
    private int queueMaxMessageChunks = 2;

    /**
     * ###################### rebalance area ###########################
     */
    /**
     * KAFKA rebalance.max.retries
     */
    private int rebalanceRetries = 4;

    /**
     * KAFKA rebalance.backoff.ms
     * Backoff time between retries during rebalance.
     */
    private long rebalanceBackOffMs = 2000;

    /**
     * KAFKA refresh.leader.backoff.ms
     */
    private long refreshLeaderBackOffMs = 200;


    /**
     * ###################### socket  area ###########################
     */
    /**
     * KAFKA socket.timeout.ms
     */
    private long socketTimeOut = 30 * 1000;

    /**
     * ###################### timeout  area ###########################
     */
    /**
     * KAFKA consumer.timeout.ms
     * Throw a timeout exception to the consumer if no message is available for consumption after the specified interval
     */
    private long consumerTimeoutMs = -1;


    /**
     * ###################### commit  area ###########################
     */
    /**
     * commit interval
     * if auto commit is
     * true:
     * KAFKA auto.commit.interval.ms
     * false:
     * manual commit interval
     */
    protected int commitInterval = 1000 * 60;

    /**
     * KAFKA dual.commit.enabled
     * sync Kafka's offset to Zookeeper
     * If you are using "kafka" as offsets.storage, you can dual commit offsets to ZooKeeper (in addition to Kafka). This is required during migration from zookeeper-based offset storage to kafka-based offset storage. With respect to any given consumer group, it is safe to turn this off after all instances within that group have been migrated to the new version that commits offsets to the broker (instead of directly to ZooKeeper).
     */
    private boolean dualCommitEnable = true;
    /**
     * KAFKA auto.commit.enable
     * if true,the config below will not effect
     */
    private boolean autoCommit = true;
    /**
     * actual message count = maxUnCommitCount * batchMessageCount
     */
    private int maxUnCommitCount = 5000;


    /**
     * ###################### offset area ###########################
     */
    /**
     * KAFKA auto.offset.reset
     * What to do when there is no initial offset in ZooKeeper or if an offset is out of range:
     * smallest : automatically reset the offset to the smallest offset
     * largest : automatically reset the offset to the largest offset
     * anything else: throw exception to the consumer
     */
    private String autoOffsetReset = "largest";

    /**
     * KAFKA exclude.internal.topics
     * internal topics (such as offsets) should be exposed to the consumer.
     */
    private boolean execludeInternalTopics = true;

    /**
     * KAFKA partition.assignment.strategy
     * roundrobin
     */
    private String partitionAssignmentStrategy = "range";

    /**
     * KAFKA offsets.storage
     */
    private String offsetStorage = "zookeeper";
    /**
     * KAFKA offsets.channel.backoff.ms
     */
    private long offsetChannelBackOffMs = 1000;
    /**
     * KAFKA offsets.channel.socket.timeout.ms
     */
    private long offsetChannelSocketTimeoutMs = 10000;

    /**
     * KAFKA offsets.commit.max.retries
     */
    private int offsetsCommitMaxRetries = 5;

    /**
     * ###################### zookeeper area ###########################
     */
    /**
     * KAFKA zookeeper.session.timeout.ms
     */
    private long zookeeperSessionTimeoutMs = 6000;
    /**
     * KAFKA zookeeper.connection.timeout.ms
     */
    private long zookeeperConnectionTimeoutMs = 6000;
    /**
     * KAFAK zookeeper.sync.time.ms
     */
    private long zookeeperSyncTimeMs = 2000;


    /**
     * construct function
     */
    public ConsumerConfig() {
        //readConfigFile(KafkaInnerConstant.DFT_CONF_FILE);
    }


    /**
     * 获取 构建 consumerConnector的参数
     *
     * @return
     */
    public Properties createConsumerConfig() {
        Properties props = new Properties();
        CheckUtil.checkStrExistNull(this.zookeeper);
        props.put("zookeeper.connect", this.zookeeper);
        CheckUtil.checkStrExistNull(this.groupId);
        props.put("group.id", this.groupId);
        props.put("zookeeper.session.timeout.ms", "1000");
        props.put("zookeeper.sync.time.ms", "1000");

        props.put("fetch.min.bytes", String.valueOf(this.fetchMinBytes));

        props.put("auto.commit.enable", "true");
        return props;
    }

    @Override
    public String toString() {
        return "ConsumerConfig{" +
                "zookeeper='" + zookeeper + '\'' +
                ", topic='" + topic + '\'' +
                ", topicHandler=" + topicHandler +
                ", groupId='" + groupId + '\'' +
                ", fetchThreadPoolSize=" + fetchThreadPoolSize +
                ", processThreadPoolSize=" + processThreadPoolSize +
                ", timingThreadPoolSize=" + timingThreadPoolSize +
                ", commitInterval=" + commitInterval +
                ", batchEventForceSendInterval=" + batchEventForceSendInterval +
                ", batchMessageCount=" + batchMessageCount +
                ", fetchMinBytes=" + fetchMinBytes +
                '}';
    }

    public String getZookeeper() {
        return zookeeper;
    }

    public void setZookeeper(String zookeeper) {
        this.zookeeper = zookeeper;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public TopicHandler getTopicHandler() {
        return topicHandler;
    }

    public void setTopicHandler(TopicHandler topicHandler) {
        this.topicHandler = topicHandler;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public int getFetchThreadPoolSize() {
        return fetchThreadPoolSize;
    }

    public void setFetchThreadPoolSize(int fetchThreadPoolSize) {
        this.fetchThreadPoolSize = fetchThreadPoolSize;
    }

    public int getProcessThreadPoolSize() {
        return processThreadPoolSize;
    }

    public void setProcessThreadPoolSize(int processThreadPoolSize) {
        this.processThreadPoolSize = processThreadPoolSize;
    }

    public int getTimingThreadPoolSize() {
        return timingThreadPoolSize;
    }

    public void setTimingThreadPoolSize(int timingThreadPoolSize) {
        this.timingThreadPoolSize = timingThreadPoolSize;
    }

    public int getCommitInterval() {
        return commitInterval;
    }

    public void setCommitInterval(int commitInterval) {
        this.commitInterval = commitInterval;
    }

    public int getBatchEventForceSendInterval() {
        return batchEventForceSendInterval;
    }

    public void setBatchEventForceSendInterval(int batchEventForceSendInterval) {
        this.batchEventForceSendInterval = batchEventForceSendInterval;
    }

    public int getBatchMessageCount() {
        return batchMessageCount;
    }

    public void setBatchMessageCount(int batchMessageCount) {
        this.batchMessageCount = batchMessageCount;
    }

    public int getFetchMinBytes() {
        return fetchMinBytes;
    }

    public void setFetchMinBytes(int fetchMinBytes) {
        this.fetchMinBytes = fetchMinBytes;
    }
}
