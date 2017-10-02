package com.atjl.kafka.core;

import com.atjl.util.character.RegexUtil;
import com.atjl.util.collection.CollectionUtil;
import com.atjl.util.number.NumberUtil;
import com.atjl.kafka.api.event.Event;
import com.atjl.kafka.api.exception.FailedFetchException;
import com.atjl.kafka.api.exception.MQException;
import com.atjl.kafka.domain.Broker;

import kafka.api.FetchRequest;
import kafka.api.FetchRequestBuilder;
import kafka.api.PartitionOffsetRequestInfo;
import kafka.common.OffsetAndMetadata;
import kafka.common.TopicAndPartition;
import kafka.javaapi.*;
import kafka.javaapi.consumer.SimpleConsumer;
import kafka.javaapi.message.ByteBufferMessageSet;

import kafka.message.Message;
import kafka.message.MessageAndOffset;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.*;

public class KafkaLowLevelUtil {

    public static final Logger LOG = LoggerFactory.getLogger(KafkaLowLevelUtil.class);
    private static final int NO_OFFSET = -5;

    private static int FETCH_SIZE_START_VAL = 100 * 1000;
    private static int FETCH_SIZE_CRITICAL_VAL = 1000 * 1000;
    private static int FETCH_SIZE_INCR_STEP = 500 * 1000;

    /**
     * 获取zk实际地址 ，需要消费者组配置 token
     *
     * @param clusterName cluster name
     * @param ctoken      token
     * @param groupid     systemid
     * @param monitorUrl  url
     * @return zk string
     */
    public static String getZk(String clusterName, String ctoken, String groupid, String monitorUrl) {
//        if (KafkaUtil.noCheckKafkaAuth()) {
//            return null;
//        }
//        String zkServers = AuthUtil.getZkServers(clusterName, ctoken, groupid, monitorUrl);
        return null;//zkServers;
    }


//    public static List<Broker>

    /**
     * 获取broker实际地址
     *
     * @param clusterName
     * @param topicToken
     * @param monitorUrl
     * @return
     */
    public static List<Broker> getBrokers(String clusterName, String topicToken, String monitorUrl) {
        String brokers = null;
        String[] brokerArr = brokers.split(",");
        if (CollectionUtil.isEmpty(brokerArr)) {
            return null;
        }
        if (LOG.isDebugEnabled()) {
            LOG.debug("get broker:{}", Arrays.toString(brokerArr));
        }
        List<Broker> res = new ArrayList<>(brokerArr.length);//Arrays.asList(brokerArr);
        for (String broker : brokerArr) {
            String[] brokerPortArr = broker.split(":");
            if (CollectionUtil.isEmpty(brokerPortArr) || brokerPortArr.length != 2 || !RegexUtil.isPositive(brokerPortArr[1])) {
                LOG.warn("getBrokers error broker string {}", broker);
                continue;
            }
            String brokerIP = brokerPortArr[0];
            String port = brokerPortArr[1];
            Broker b = new Broker(brokerIP, Integer.parseInt(port));
            res.add(b);
        }
        return res;
    }

    /**
     * find leader broker of specified topic's partition
     *
     * @param seedBrokers all brokers
     * @param topic       topic
     * @param partition   partition id
     * @retura leader broker
     */
    public static Broker getLeaderBroker(List<Broker> seedBrokers, String topic, int partition) {
        PartitionMetadata metadata = findLeaderInner(seedBrokers, topic, partition);
        if (metadata == null) {
            LOG.error("Can't find metadata for Topic and Partition.");
            return null;
        }
        if (metadata.leader() == null) {
            LOG.error("Can't find Leader for Topic and Partition.");
            return null;
        }
        String leadBroker = metadata.leader().host();
        int port = metadata.leader().port();
        return new Broker(leadBroker, port);
    }


    /**
     * generate simple consumer
     *
     * @param leaderBroker leader broker
     * @param name         consumer name
     * @return simple consumer
     */
    public static SimpleConsumer getSimpleConsumer(Broker leaderBroker, String name) {
        SimpleConsumer consumer = new SimpleConsumer(leaderBroker.host, leaderBroker.port, 100000, 64 * 1024, name);
        return consumer;
    }


    /**
     * get offset now
     *
     * @param consumer  simple consumer
     * @param topic     topic
     * @param partition partition id
     * @return last offset
     */
    public static long getProducedOffset(SimpleConsumer consumer, String topic, int partition) {
        long startOffsetTime = kafka.api.OffsetRequest.LatestTime();

        TopicAndPartition topicAndPartition = new TopicAndPartition(topic, partition);
        Map<TopicAndPartition, PartitionOffsetRequestInfo> requestInfo = new HashMap<>();
        requestInfo.put(topicAndPartition, new PartitionOffsetRequestInfo(startOffsetTime, 1));

        OffsetRequest request = new OffsetRequest(
                requestInfo, kafka.api.OffsetRequest.CurrentVersion(), consumer.clientId());

        long[] offsets = consumer.getOffsetsBefore(request).offsets(topic, partition);
        if (offsets.length > 0) {
            return offsets[0];
        } else {
            return NO_OFFSET;
        }
    }

    /**
     * 获取消费的offset
     *
     * @param simpleConsumer simple consumer
     * @param topic          topic
     * @param partitionid    partition id
     * @return commited last offset
     */
    public static long getCommitedOffset(SimpleConsumer simpleConsumer, String topic, int partitionid) {
        short versionID = 0;
        int correlationId = 0;
        List<TopicAndPartition> topicPartitionList = new ArrayList<>();
        TopicAndPartition topicAndPartition = new TopicAndPartition(topic, partitionid);
        topicPartitionList.add(topicAndPartition);
        OffsetFetchRequest offsetFetchReq = new OffsetFetchRequest(
                simpleConsumer.clientId(), topicPartitionList, versionID, correlationId, simpleConsumer.clientId());
        OffsetFetchResponse offsetFetchResponse = simpleConsumer.fetchOffsets(offsetFetchReq);
        long currentOffset = offsetFetchResponse.offsets().get(topicAndPartition).offset();
        return currentOffset;
    }

    /**
     * get offset before timestamp[ms]
     *
     * @param sc          simple consumer
     * @param topic       topic
     * @param partitionid parititon id
     * @param ts          timestamp [ms]
     * @return offset, only can get the cloest offset
     */
    public static long getOffsetBefore(SimpleConsumer sc, String topic, int partitionid, long ts) {
        short versionID = 0;

        TopicAndPartition topicAndPartition = new TopicAndPartition(topic, partitionid);
        Map<TopicAndPartition, PartitionOffsetRequestInfo> map = new HashMap<>();
        PartitionOffsetRequestInfo pi = new PartitionOffsetRequestInfo(ts, 1);
        map.put(topicAndPartition, pi);

        OffsetRequest req = new OffsetRequest(map, versionID, sc.clientId());
        OffsetResponse offsetFetchResponse = sc.getOffsetsBefore(req);
        long[] offsets = offsetFetchResponse.offsets(topic, partitionid);
        //System.out.println(Arrays.toString(offsets));
        if (offsets.length > 0) {
            return offsets[0];
        } else {
            return NO_OFFSET;
        }
    }


    /**
     * str2ts message set to event
     *
     * @param msgSet msgset
     * @return list of event(offset,key,value setted)
     * @throws UnsupportedEncodingException message value encoding unknown
     */
    public static List<Event> parseMessageSet2Event(ByteBufferMessageSet msgSet) throws UnsupportedEncodingException {
        List<Event> list = new LinkedList<>();
        for (MessageAndOffset messageAndOffset : msgSet) {
            Event e = new Event();

            Message msg = messageAndOffset.message();
            long currentOffset = messageAndOffset.offset();
            e.setOffset(currentOffset);
            //long nextOffset = messageAndOffset.nextOffset();

            ByteBuffer payload = msg.payload();
            byte[] bytes = new byte[payload.limit()];

            payload.get(bytes);
            String value = new String(bytes, "UTF-8");
            if (LOG.isDebugEnabled()) {
                //LOG.debug("value size :{},msg size:{}", bytes.length, msg.size());
            }
            e.setValue(value);
            if (msg.hasKey()) {
                ByteBuffer key = msg.key();
                byte[] keyBtyes = new byte[key.limit()];
                key.get(keyBtyes);
                e.setKey(new String(keyBtyes, "UTF-8"));
            }
            list.add(e);
        }
        return list;
    }

    /**
     * fetch message and str2ts to Event list
     *
     * @param consumer    simple consumer
     * @param topic       topic
     * @param partitionId partition id
     * @param startOffset resume offset
     * @param size        fetchsize
     * @return event list
     * @throws UnsupportedEncodingException message value encoding unknown
     */
    public static List<Event> fetchEvent(SimpleConsumer consumer,
                                         String topic, int partitionId,
                                         long startOffset, int size) throws UnsupportedEncodingException {
        return parseMessageSet2Event(fetchMessagesInner(consumer, topic, partitionId, startOffset, size));
    }


    /**
     * fetch message inner
     *
     * @param consumer    simple consumer
     * @param startOffset resume offset
     * @param size        fetch size
     * @return raw message set
     */
    public static ByteBufferMessageSet fetchMessagesInner(SimpleConsumer consumer,
                                                          String topic, int partitionId,
                                                          long startOffset, int size) {
        if (size <= 0) {
            size = 1;
        }
        ByteBufferMessageSet msgs = null;
        for (int errors = 0; errors < 2 && msgs == null; errors++) {
            FetchRequestBuilder builder = new FetchRequestBuilder();
            FetchRequest fetchRequest = builder
                    .addFetch(topic, partitionId, startOffset, size)
                    .clientId(consumer.clientId())
                    .build();
            FetchResponse fetchResponse;
            try {
                fetchResponse = consumer.fetch(fetchRequest);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            if (fetchResponse.hasError()) {
                String error = null;
                //MQCode error = MQCode.getError(fetchResponse.errorCode(topic, partitionId));
                //res.setCode(error);
                String msg = String.format("Error fetching data from topic [%s] parititon [%s]: [%s]", topic, partitionId, error);
                LOG.error(msg);
                throw new FailedFetchException(error + "");
            } else {
                msgs = fetchResponse.messageSet(topic, partitionId);
            }
        }
        return msgs;
    }

    /**
     * find leader broker by seed brokers(use setting broker or use )
     *
     * @param seedBrokers
     * @param topic
     * @param partition
     * @return
     */
    private static PartitionMetadata findLeaderInner(List<Broker> seedBrokers, String topic, int partition) {
        PartitionMetadata returnMetaData = null;
        loop:
        for (Broker seed : seedBrokers) {
            SimpleConsumer findLeaderConsumer = null;
            try {
                findLeaderConsumer = new SimpleConsumer(seed.host, seed.port, 100000, 64 * 1024, "leaderLookup");
                List<String> topics = Collections.singletonList(topic);
                TopicMetadataRequest req = new TopicMetadataRequest(topics);
                kafka.javaapi.TopicMetadataResponse resp = findLeaderConsumer.send(req);

                List<TopicMetadata> metaData = resp.topicsMetadata();
                for (TopicMetadata item : metaData) {
                    for (PartitionMetadata part : item.partitionsMetadata()) {
                        if (part.partitionId() == partition) {
                            returnMetaData = part;
                            break loop;
                        }
                    }
                }
            } catch (Exception e) {
                LOG.error("Error communicating with Broker [ {} ] to find Leader for [ topic {} ,pritition: {} ] Reason: {}", seed, topic, partition, e);
            } finally {
                if (findLeaderConsumer != null) findLeaderConsumer.close();
            }
        }

        /**
         * add replica to list
         *
         if (returnMetaData != null) {
         if(replicaBrokers ==null){
         System.out.println("replica broker not init");
         }else {
         replicaBrokers.clear();
         for (kafka.cluster.Broker replica : returnMetaData.replicas()) {
         replicaBrokers.add(new Broker(replica.host(),replica.port()));
         }
         }
         }*/
        return returnMetaData;
    }

    /**
     * save commit
     *
     * @param sc        simple consumer
     * @param groupid   consumer group id
     * @param topic     topic
     * @param partition partition id
     * @param offset    commit offset
     * @return errorcode
     * @throws Exception
     */
    public static short saveOffset(SimpleConsumer sc, String groupid, String topic, int partition, long offset, int type) throws Exception {
        if (LOG.isDebugEnabled()) {
            LOG.debug("Starting to save the Offset value to Kafka: offset={}, errorCode={} for partition {}", offset, partition);
        }
        short versionID = 0;
        int correlationId = type;// version 1 and above commit to Kafka, version 0 commits to ZooKeeper
        long now = System.currentTimeMillis();
        try {
            TopicAndPartition tp = new TopicAndPartition(topic, partition);
            OffsetAndMetadata offsetMetaAndErr = new OffsetAndMetadata(
                    offset, OffsetAndMetadata.NoMetadata(), now);
            Map<TopicAndPartition, OffsetAndMetadata> mapForCommitOffset = new HashMap<>();
            mapForCommitOffset.put(tp, offsetMetaAndErr);
            //TODO: client id need generate
            OffsetCommitRequest offsetCommitReq = new OffsetCommitRequest(groupid, mapForCommitOffset, correlationId, groupid, versionID);
            OffsetCommitResponse offsetCommitResp = sc.commitOffsets(offsetCommitReq);
            if (LOG.isDebugEnabled()) {
                LOG.debug("Completed OffsetSet commitPartition for partition {}. OffsetCommitResponse ErrorCode = {} Returning to caller ", partition, offsetCommitResp.errors().get(tp));
            }
            return (Short) offsetCommitResp.errors().get(tp);
        } catch (Exception e) {
            String msg = String.format("commiting unknown exception %s", e.getMessage());
            LOG.error(msg);
            throw new MQException(msg);
        }
    }

    /**
     * if cant't fetch message use size,may be the message is too big,increase the size
     *
     * @param size fetch size
     * @return next size
     */
    public static int getNextSize(int size) {
        if (size <= 0) {
            size = FETCH_SIZE_START_VAL;
        } else {
            if (size < FETCH_SIZE_CRITICAL_VAL) {//小于临界值，倍增
                return size * 2;
            } else {//大于临界值，按固定大小增加
                return size + FETCH_SIZE_INCR_STEP;
            }
        }
        return size;
    }

    /**
     * calc next fetch size, to get more message in one time fetch
     * situation 1:
     * end > read+fetchMaxCount
     * preFetchSize                 res
     * |--------------------|------------------------------|-------------------| ...... |
     * offset: resume                read                   read+fetchMaxCount        end    max
     * <p>
     * msgsize = preFetchSize/(read-resume+1)
     * <p>
     * calc
     * res = msgsize * fetchMaxCount
     * <p>
     * next read:
     * offset = read
     * fetchSize = res
     * <p>
     * situation 2:
     * end<= read+fetchMaxCount
     * preFetchSize                 res
     * |--------------------|--------------------------|..........|.................|
     * offset: resume                read                        end   read+fetchMaxCount     max
     * msgsize = preFetchSize/(read-resume+1)
     * res = msgsize* (end-read+1)
     *
     * @param preFetchTotalSize pre fetch size
     * @param startOffset       resume offset
     * @param readOffset        read offset
     * @param endOffset         end offset
     * @return next fetch size
     */
    public static int getNextSizeByPreFetchSize(int preFetchTotalSize, long startOffset, long readOffset, long endOffset, int fetchCountMax) {
        //long diff = readOffset - startOffset + 1;
        double total = preFetchTotalSize;
        double diff = readOffset - startOffset;

        if (diff <= 0) {
            String msg = String.format("readoffset %s small than startOffset %s", readOffset, startOffset);
            throw new IllegalArgumentException(msg);
        }
        if (endOffset < readOffset) {
            String msg = String.format("endOffset %s less equal to readOffset %s", endOffset, readOffset);
            throw new IllegalArgumentException(msg);
        }
        //ceil the msg size
        double msgSizeD = Math.ceil(total / diff);
        long msgSizeL = Math.round(msgSizeD);
        if (LOG.isDebugEnabled()) {
            LOG.debug("prefetch {},diff {},msgsize {}", preFetchTotalSize, diff, msgSizeL);
        }

        long nextFetchSize = 0;
        //S1: read+max < endoffset
        if (readOffset + fetchCountMax < endOffset) {
            nextFetchSize = fetchCountMax * msgSizeL;
        } else {
            //S2: read+max >= end offset
            nextFetchSize = (endOffset - readOffset + 1) * msgSizeL;
        }
        int res = NumberUtil.long2Int(nextFetchSize);
        return res;
    }


    /**
     * commit offset
     *
     * @param simpleConsumer simple consumer
     * @param topic          topic
     * @param partition      partition
     * @param offset         target offset
     * @return
     */
    public static int commitOffset(SimpleConsumer simpleConsumer, String topic, int partition, long offset) {
        LOG.debug("commit offset topic {},partition {},offset {}", topic, partition, offset);
        short versionID = 0;
        int correlationId = 0;
        long now = System.currentTimeMillis();
        TopicAndPartition tp = new TopicAndPartition(topic, partition);
        OffsetAndMetadata offsetMetaAndErr = new OffsetAndMetadata(
                offset, OffsetAndMetadata.NoMetadata(), now);
        Map<TopicAndPartition, OffsetAndMetadata> mapForCommitOffset = new HashMap<>();
        mapForCommitOffset.put(tp, offsetMetaAndErr);
        OffsetCommitRequest offsetCommitReq = new OffsetCommitRequest(simpleConsumer.clientId()
                , mapForCommitOffset, correlationId, simpleConsumer.clientId(), versionID);
        OffsetCommitResponse offsetCommitResp = simpleConsumer.commitOffsets(offsetCommitReq);
        LOG.debug("Completed OffsetSet commitPartition for partition {}. OffsetCommitResponse ErrorCode = {} Returning to caller ", partition, offsetCommitResp.errors().get(tp));

        return (Short) offsetCommitResp.errors().get(tp);
    }


    /**
     * @param sc
     * @param topic
     * @param partition
     * @param offset
     * @return
     */
    public static boolean checkOffset(SimpleConsumer sc, String topic, int partition, long offset) {
        long lastOffset = getProducedOffset(sc, topic, partition);
        //sc.close();
        //检查offset是否已经被消费过
        if (offset > lastOffset) {
            String msg = String.format("has not consume to %s", offset);
            LOG.error(msg);
            //throw new MQException(msg);
            return false;
        }
        return true;
    }
}
