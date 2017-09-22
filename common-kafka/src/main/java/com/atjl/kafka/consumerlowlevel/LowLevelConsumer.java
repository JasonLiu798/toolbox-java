package com.atjl.kafka.consumerlowlevel;

import com.atjl.common.api.LifeCycle;
import com.atjl.kafka.api.domain.MQCode;
import com.atjl.kafka.api.exception.GetZookeeperException;
import com.atjl.kafka.consumerlowlevel.cache.BrokerCache;
import com.atjl.kafka.consumerlowlevel.cache.SimpleConsumerCache;
import com.atjl.kafka.core.KafkaLowLevelUtil;
import com.atjl.kafka.domain.*;
import com.atjl.kafka.domain.constant.KafkaConfigConstant;
import com.atjl.util.character.StringCheckUtil;
import com.atjl.util.collection.CollectionUtil;
import com.atjl.util.number.NumberUtil;
import com.atjl.kafka.api.event.Event;
import com.atjl.kafka.api.exception.MQException;

import kafka.javaapi.consumer.SimpleConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Kafka consumer Simple Consumer API 封装
 * 多次 消费，补录数据 用
 * <p>
 * use high level consumer API
 * for version <= 0.8.2
 * <p>
 * ZookeeperConsumerConnector
 * Consumer:
 * /consumers/[group_id]/ids[consumer_id] -> topic1,...topicN
 * <p>
 * Broker:
 * /brokers/[0...N] --> { "host" : "host:port",
 * "topics" : {"topic1": ["partition1" ... "partitionN"], ...,
 * "topicN": ["partition1" ... "partitionN"] } }
 * <p>
 * Partition owner registry:
 * /consumers/[group_id]/owner/[topic]/[broker_id-partition_id] --> consumer_node_id
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
@Component
public class LowLevelConsumer implements LifeCycle {
    private static Logger logger = LoggerFactory.getLogger(LowLevelConsumer.class);
    @Value("${kafka.spropfile:kafka.lowlevel.properties}")
    private String propertiesFile;
    @Resource
    private BrokerCache brokerCache;
    //@Resource
    //private ConsumerIdCache consumerIdCache;
    @Resource
    private SimpleConsumerCache simpleConsumerCache;
    //    @Resource
//    private ZkServiceService zkServiceService;
    private LowLevelConsumerConfig config;
    private boolean initialed = false;

    /**
     * K:topic
     * V:list of brokers
     * private Map<String, List<Broker>> topicBrokersMap = new ConcurrentHashMap<>();
     */
    private Map<String, Integer> topicFetchSizeMap = new ConcurrentHashMap<>();

    public LowLevelConsumer() {
    }

    public LowLevelConsumer(String propertiesFile) {
        this.propertiesFile = propertiesFile;
    }

    /**
     * init config model
     * init every topic's broker list
     */
    @Override
    @PostConstruct
    public void init() {
        try {
            if (this.config == null) {
                if (StringCheckUtil.isEmpty(this.propertiesFile)) {
                    this.propertiesFile = KafkaConfigConstant.DFT_CONF_FILE;
                }
                logger.info("config file,lowlevel consumer {}", this.propertiesFile);
//                LowLevelConsumerConfig config = ConfigModelUtil.generateConfigModel(LowLevelConsumerConfig.class, KafkaConfigConstant.CONF_SC_PREFIX, this.propertiesFile, LowLevelConsumerConfig.EXECLUDE_FIELDS);
//                this.config = config;
            }
            logger.info("config content,lowlevel consumer {}", this.config);
            Map<String, TopicMeta> map = this.config.buildTopicMap();

            if (CollectionUtil.isEmpty(map)) {
                String msg = String.format("topic map init error,with config %s", this.config);
                throw new MQException(msg);
            }
            initBrokerList();
        } catch (Exception e) {
            logger.error("init lowlevel consumer fail", e);
        }
        initialed = true;
    }

    /**
     * init topic's broker list to topicBrokersMap,for get simpleConsumer
     */
    private void initBrokerList() {
        Map<String, TopicMeta> map = this.config.buildTopicMap();
        logger.debug("topic map builded {}", map);
        for (Map.Entry<String, TopicMeta> entry : map.entrySet()) {
            TopicMeta tm = entry.getValue();
            List<Broker> brokers = KafkaLowLevelUtil.getBrokers(tm.getCluster(), tm.getTopicToken(), config.getUrl());
            brokerCache.put(tm.getTopic(), brokers);
            if (logger.isInfoEnabled()) {
                logger.info("add broker,topic {},broker {}", entry.getKey(), brokers);
            }
        }
        if (logger.isDebugEnabled()) {
            logger.info("init cache broker {}", brokerCache.getStatus());
        }
    }

    @Override
    @PreDestroy
    public void destroy() {
    }

    /**
     * get simple consumer use topic,parititon,leader broker(broker list(url,cluster name,topic token,),topic,partition)
     *
     * @param topic     topic
     * @param partition partition id
     * @return
     */
    public SimpleConsumer getOrInitSimpleConsumer(String topic, String groupid, int partition) throws NullPointerException {
        String key = SimpleConsumerCache.formatKey(topic, groupid, partition);
        SimpleConsumer sc = simpleConsumerCache.get(key);
        if (sc != null) {
            return sc;
        }
        //初始化 simple consumer
        List<Broker> brokerList = brokerCache.get(topic);
        Broker leader = KafkaLowLevelUtil.getLeaderBroker(brokerList, topic, partition);
        sc = KafkaLowLevelUtil.getSimpleConsumer(leader, groupid);
        if (sc == null) {
            throw new NullPointerException(String.format("init simple consumer null,group %s,topic %s,partition %s", groupid, topic, partition));
        }
        simpleConsumerCache.put(key, sc);
        return sc;
    }

    /**
     * @param topic
     * @return
     */
    public int getOrInitFetchSizeBytes(String topic) {
        Integer fetchSize = topicFetchSizeMap.get(topic);
        if (fetchSize == null) {
            fetchSize = config.getFetchSizeBytes();
            topicFetchSizeMap.put(topic, fetchSize);
        }
        return fetchSize;
    }

    /**
     * @param topic
     * @param partition
     * @return
     */
    public long getCommitedOffset(String topic, String groupid, int partition) throws Exception {
        SimpleConsumer sc = getOrInitSimpleConsumer(topic, groupid, partition);
        return KafkaLowLevelUtil.getCommitedOffset(sc, topic, partition);
    }

    /**
     * @param topic
     * @param partition
     * @return
     */
    public long getProducedOffset(String topic, String groupid, int partition) throws Exception {
        SimpleConsumer sc = getOrInitSimpleConsumer(topic, groupid, partition);
        return KafkaLowLevelUtil.getProducedOffset(sc, topic, partition);
    }

    /**
     * get zookeeper url
     *
     * @return
     */
    public Map<String, String> getZkUrls() {
        Map<String, TopicMeta> map = this.config.buildTopicMap();
        Map<String, String> zkMap = new HashMap<>();
        for (Map.Entry<String, TopicMeta> entry : map.entrySet()) {
            TopicMeta tm = entry.getValue();
            String zk = KafkaLowLevelUtil.getZk(tm.getCluster(), tm.getCtoken(), tm.getGroupid(), this.config.getUrl());
            zkMap.put(tm.getTopic(), zk);
        }
        return zkMap;
    }

    /**
     * get zk url need:cluster,ctoken,groupid,url
     *
     * @param topic
     * @param groupid
     * @return
     */
    public String getZkUrl(String topic, String groupid) throws Exception {
        Map<String, TopicMeta> map = this.config.buildTopicMap();
        TopicMeta tm = map.get(TopicMeta.fromatKey(topic, groupid));
        if (tm == null) {
            throw new IllegalArgumentException(String.format("topic %s,group %s,not exist", topic, groupid));
        }
        String zk = KafkaLowLevelUtil.getZk(tm.getCluster(), tm.getCtoken(), tm.getGroupid(), this.config.getUrl());
        return zk;
    }

    /**
     * get zkservice
     *
     * @param topic
     * @param groupid
     * @return
     * @throws Exception
     *
    private ZkService getZkService(String topic, String groupid) throws Exception{
    String url = getZkUrl(topic, groupid);
    //zkServiceService.get();
    ResponseDataDto resp = zkServiceService.get(topic);
    if (resp.getCode() != CommonOuterErrorCode.SUCCESS) {
    ResponseDataDto addResp = zkServiceService.add(topic, url);
    if (addResp.getCode() == CommonOuterErrorCode.SUCCESS) {
    return (ZkService) addResp.getData();
    } else {
    return null;
    }
    //logger.info("zkcache after add status:{}", zkCache.getStatus());
    } else {
    return (ZkService) resp.getData();
    }
    }*/


    /**
     * get topic consumer,offset
     *
     * @return
     * @throws Exception
     */
    public Map<String, TopicPartiton> getTopicInfo() throws Exception {
        Map<String, TopicPartiton> map = getPartitionOwner();
        if (logger.isDebugEnabled()) {
            logger.debug("topic tp map {}", map);
        }
        for (Map.Entry<String, TopicPartiton> entry : map.entrySet()) {
            TopicPartiton tp = entry.getValue();
            filterAddConsumeStats(tp.getTopic(), tp.getGroupid(), tp);
        }
        return map;
    }

    /**
     * get topic's consumer's id
     *
     * @return
     * @throws Exception
     */
    public Map<String, TopicPartiton> getPartitionOwner() throws Exception {
        Map<String, TopicMeta> map = this.config.getTopicMap();
        if (logger.isDebugEnabled()) {
            logger.debug("topic map {}", map);
        }

        Map<String, TopicPartiton> res = new HashMap<>();
        for (Map.Entry<String, TopicMeta> entry : map.entrySet()) {
            TopicMeta tm = entry.getValue();

            TopicPartiton tp = new TopicPartiton(tm.getTopic(), tm.getGroupid());

            //ZkService zkService = getZkService(tm.getTopic(), tm.getGroupid());

//            if (zkService == null) {
//                throw new NullPointerException("get zkservice null");
//            }
            List<Integer> parititions = getPartitions(tm.getTopic(), tm.getGroupid());

            String topic = tm.getTopic();
            for (Integer pid : parititions) {
                String path = String.format(ZkConstant.CONSUMER_PARTITION_OWNER_PATH, tm.getGroupid(), tm.getTopic(), pid);
                String data = null;
                try {
//                    data = zkService.getData(path);
                } catch (Exception e) {
                    //logger.error("get node error ,path {}", path);
                    continue;
                }
                PartitionMeta pm = new PartitionMeta();
                pm.setOwnerid(data);
                pm.setPid(pid);
                pm.setTopic(topic);
                tp.addPartition(pm);
            }
            res.put(tp.getTopicGroupid(), tp);
        }
        return res;
    }


    /**
     * get consumer topic's partition
     *
     * @param topic
     * @return
     */
    public List<Integer> getPartitions(String topic, String groupid) throws Exception {
//        ZkService zk = getZkService(topic, groupid);
        Object zk = null;
        if (zk == null) {
            throw new GetZookeeperException(String.format("topic %s,groupid %s", topic, groupid));
        }

        List<String> list = null;
        Map<String, TopicMeta> map = this.config.getTopicMap();
        TopicMeta tm = map.get(TopicMeta.fromatKey(topic, groupid));
        try {
            //"/consumers/A1C/offsets/A1"
            String path = String.format(ZkConstant.CONSUMER_TOPIC_OFFSET_PARENT_PATH, tm.getGroupid(), topic);
//            list = zk.getChilds(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<Integer> res = new LinkedList<>();
        if (CollectionUtil.isEmpty(list)) {
            return res;
        }
        for (String str : list) {
            res.add(Integer.parseInt(str));
        }
        return res;
    }

    public void filterAddConsumeStats(String topic, String groupid, TopicPartiton tp) throws Exception {
        addConsumeState(topic, groupid, tp, true);
    }

    /**
     * get all partition's process status
     *
     * @param groupid
     * @param topic
     * @return
     * @throws IOException
     */
    public TopicPartiton getAllPartitionConsumeState(String groupid, String topic) throws Exception {
        TopicPartiton tp = new TopicPartiton();
        tp.setTopic(topic);

        List<Integer> partitions = getPartitions(topic, groupid);
        for (Integer pid : partitions) {
            PartitionMeta pm = new PartitionMeta();
            SimpleConsumer sc = getOrInitSimpleConsumer(topic, groupid, pid);

            long consumedOffset = KafkaLowLevelUtil.getCommitedOffset(sc, topic, pid);
            long produceOffset = KafkaLowLevelUtil.getProducedOffset(sc, topic, pid);
            pm.setTopic(topic);
            pm.setPid(pid);
            pm.setConsumedOffset(consumedOffset);
            pm.setProducedOffset(produceOffset);
            tp.addPartition(pm);
        }
        return tp;
    }

    /**
     * @param topic
     * @param groupid
     * @param tp
     * @param filterAdd
     */
    private void addConsumeState(String topic, String groupid, TopicPartiton tp, boolean filterAdd) throws Exception {
        List<Integer> partitions = getPartitions(topic, groupid);
        for (Integer pid : partitions) {
            PartitionMeta pm = null;
            if (!filterAdd) {
//                pm = new PartitionMeta();
//                pm.setTopic(topic);
//                pm.setPid(pid);
            } else {
                pm = tp.getPartition(pid);
                if (pm == null) {
                    //logger.error("topic {},parition {},get partition null", topic, pid);
                    continue;
                }
            }
            SimpleConsumer sc = getOrInitSimpleConsumer(topic, groupid, pid);
            long consumedOffset = KafkaLowLevelUtil.getCommitedOffset(sc, topic, pid);
            long produceOffset = KafkaLowLevelUtil.getProducedOffset(sc, topic, pid);
            pm.setConsumedOffset(consumedOffset);
            pm.setProducedOffset(produceOffset);
            pm.setRemain(produceOffset - consumedOffset);
        }
    }

    /**
     * @param groupid
     * @param topic
     * @param partition
     * @param offset    message's offset, 0<=offset<=consumed offset
     * @return result
     */
    public ResultDto<Event> fetchOneCommitedEvent(String groupid, String topic, int partition, long offset) {
        SimpleConsumer sc = getOrInitSimpleConsumer(topic, groupid, partition);
        long commitedOffset = KafkaLowLevelUtil.getCommitedOffset(sc, topic, partition);
        if (offset > commitedOffset) {
            return new ResultDto<>(MQCode.OFFSET_OUT_OF_RANGE);
        }
        return fetchOneProducedEvent(groupid, topic, partition, offset);
    }

    /**
     * 拉取一条offset消息，不影响zk offset
     *
     * @param topic     topic
     * @param partition partition
     * @param offset    offset
     * @return message
     */
    public ResultDto<Event> fetchOneProducedEvent(String groupid, String topic, int partition, long offset) {
        ResultDto<Event> ret = new ResultDto<>();

        Event res = new Event();
        SimpleConsumer sc = getOrInitSimpleConsumer(topic, groupid, partition);
        if (sc == null) {
            ret.setCode(MQCode.INIT_SIMPLE_CONSUMER_ERROR);
            return ret;
        }
        //check offset
        long lastOffset = KafkaLowLevelUtil.getProducedOffset(sc, topic, partition);

        //检查offset是否已经被生产过
        if (offset > lastOffset) {
            String msg = String.format("has not consume to %s", offset);
            logger.error(msg);
            ret.setCode(MQCode.HAS_NOT_BEEN_CONSUME);
            return ret;
        }

        List<Event> list = null;
        int fetchSize = getOrInitFetchSizeBytes(topic);
        int retryTime = config.getFetchSizeIncreaseRetryTime();

        for (int i = 0; i < retryTime; i++) {
            try {
                list = KafkaLowLevelUtil.fetchEvent(sc, topic, partition, offset, fetchSize);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                String msg = String.format("message encoding unknow,can't decode bye UTF-8");
                throw new MQException(msg);
            }
            if (CollectionUtil.isEmpty(list)) {//无法获取到消息，增加获取size大小
                fetchSize = KafkaLowLevelUtil.getNextSize(fetchSize);
                topicFetchSizeMap.put(topic, fetchSize);
            } else {
                //不为空，已经拉取到数据
                break;
            }
        }
        if (CollectionUtil.isEmpty(list)) {
            ret.setCode(MQCode.NO_DATA_GET);
            return ret;
        } else {
            for (Event e : list) {
                if (e.getOffset() == offset) {
                    res.setTopic(topic);
                    res.setPartition(partition);
                    res.setOffset(offset);
                    res.setValue(e.getValue());
                    ret.setData(res);
                    break;
                }
            }
        }
        return ret;
    }

    /**
     * 拉取多条消息，最大不超过 getMessageCountMaxSize
     * S1:  enc<=fetchSize
     * |-------------------|..........|
     * resume               end         fetchSize
     * S2:
     * |---------------------------|------------------|
     * resume                       fetchSize           end
     * <p>
     * <p>
     * S3:
     * |--------------------------
     *
     * @param topic       topic
     * @param parition    partition id
     * @param startOffset resume offset
     * @param endoffset   end offset
     * @return list events
     */
    @SuppressWarnings("unused")
    public ResultDto<List<Event>> fetchEventRange(String groupid, String topic, int parition, long startOffset, long endoffset) throws UnsupportedEncodingException {
        //check parameter
        long totalCount = endoffset - startOffset + 1;
        if (startOffset < 0 || endoffset <= 0 || totalCount <= 0) {
            String msg = String.format("resume %s or end %s offset error", startOffset, endoffset);
            throw new IllegalArgumentException(msg);
        }
        //check totalCount is over the max size
        if (totalCount > config.getFetchEventCountMax()) {
            throw new IllegalArgumentException("fetch too many,only can fetch");
        }
        //init simpleConsumer
        SimpleConsumer sc = getOrInitSimpleConsumer(topic, groupid, parition);
        if (sc == null) {
            throw new MQException("init consumer error");
        }
        //init fetchSize
        int fetchSize = getOrInitFetchSizeBytes(topic);
        if (logger.isDebugEnabled()) {
            logger.debug("fetch size {}", fetchSize);
        }
        //first fetch
        List<Event> firstGetEvents = KafkaLowLevelUtil.fetchEvent(sc, topic, parition, startOffset, fetchSize);
        //Situation1: just got the whole events
        if (totalCount <= firstGetEvents.size()) {
            firstGetEvents = filterEvents(firstGetEvents, startOffset, endoffset);
            return new ResultDto<>(firstGetEvents);
        }

        //Situation2: need more fetch
        if (logger.isDebugEnabled()) {
            logger.debug("first get {},left {}", firstGetEvents.size(), totalCount - firstGetEvents.size());
        }
        List<Event> resList = new LinkedList<>();//result list
        resList.addAll(firstGetEvents);//add the first result
        int alreadyReadCount = firstGetEvents.size();//init read event count
        long read = startOffset + alreadyReadCount;
        //init next
        int fetchedTotalBytes = fetchSize;
        int incrMsgSizeCount = 0;
        //loop to fetch more until get all
        while (alreadyReadCount < totalCount) {
            int fetchDynSize = KafkaLowLevelUtil.getNextSizeByPreFetchSize(fetchedTotalBytes, startOffset, read, endoffset, config.getInnerFetchEventCountMax());//get dynamic fetch size

            if (logger.isDebugEnabled()) {
                logger.debug("getmore read {},rcnt {},end {},fetchsize:{}", read, alreadyReadCount, endoffset, fetchDynSize);
            }

            List<Event> partEvents = KafkaLowLevelUtil.fetchEvent(sc, topic, parition, read, fetchDynSize);
            if (logger.isDebugEnabled()) {
                logger.debug("actual get {}", partEvents.size());
            }
            if (CollectionUtil.isEmpty(partEvents)) {
                //message size too big
                incrMsgSizeCount++;


                // config.
                //return new ResponseInnerDto<>(MQCode.FETCH_DATA_ERROR);
            }
            if (alreadyReadCount + partEvents.size() >= totalCount) {
                resList.addAll(partEvents);
                resList = filterEvents(resList, startOffset, endoffset);
                return new ResultDto<>(MQCode.SUCCESS, resList);
            } else {
                alreadyReadCount += partEvents.size();
                resList.addAll(partEvents);
                read = startOffset + alreadyReadCount;

                fetchedTotalBytes += fetchDynSize;
            }
        }
        return new ResultDto<>(MQCode.FETCH_DATA_ERROR);
    }

    private List<Event> filterEvents(List<Event> list, long st, long et) {
        Iterator<Event> it = list.iterator();
        while (it.hasNext()) {
            Event e = it.next();
            if (e.getOffset() < st || e.getOffset() > et) {
                it.remove();
            }
        }
        return list;
    }

    @SuppressWarnings("unused")
    private long getMaxOffset(List<Event> list) {
        if (CollectionUtil.isEmpty(list)) {
            return -1;
        }
        long maxOffset = 0;
        for (Event e : list) {
            if (e.getOffset() > maxOffset) {
                maxOffset = e.getOffset();
            }
        }
        return maxOffset;
    }

    /**
     * use actual data calc every msg size,may little bigger than actual
     *
     * @param startOffset
     * @param endOffset
     * @param fetchSize
     * @return
     */
    @SuppressWarnings("unused")
    private int calcMsgSize(long startOffset, long endOffset, int fetchSize) {
        long count = endOffset - startOffset + 1;
        long size = fetchSize / count;
        return NumberUtil.long2Int(size);
    }


    /**
     * reset offset to 0
     *
     * @param topic     topic
     * @param partition topic partition
     */
    public void resetOffset(String groupid, String topic, int partition) {
        resetOffset2SpecTime(groupid, topic, partition, -1);
    }

    /**
     * TODO:
     *
     * @param groupid
     * @param topic
     * @param partition
     * @param time
     */
    public void resetOffset2SpecTime(String groupid, String topic, int partition, long time) {
        long resetOffset = 0;
        SimpleConsumer sc = getOrInitSimpleConsumer(topic, groupid, partition);
        if (time < 0) {
            KafkaLowLevelUtil.commitOffset(sc, topic, partition, resetOffset);
        } else {

        }
    }

    /**
     * get commited offset
     *
     * @param groupid   group id
     * @param topic     topic
     * @param partition parititon id
     */
    public long getConsumedOffset(String groupid, String topic, int partition) {
        SimpleConsumer sc = getOrInitSimpleConsumer(topic, groupid, partition);
        return KafkaLowLevelUtil.getCommitedOffset(sc, topic, partition);
    }

    public String getPropertiesFile() {
        return propertiesFile;
    }

    public LowLevelConsumerConfig getConfig() {
        return config;
    }

    public void setConfig(LowLevelConsumerConfig config) {
        this.config = config;
    }

    public void setPropertiesFile(String propertiesFile) {
        this.propertiesFile = propertiesFile;
    }

    public boolean isInitialed() {
        return initialed;
    }

    public void setInitialed(boolean initialed) {
        this.initialed = initialed;
    }

}
