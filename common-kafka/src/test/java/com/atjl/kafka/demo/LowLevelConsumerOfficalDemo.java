package com.atjl.kafka.demo;

import kafka.api.ConsumerMetadataRequest;
import kafka.api.FetchRequest;
import kafka.api.FetchRequestBuilder;
import kafka.api.PartitionOffsetRequestInfo;
import kafka.cluster.Broker;
import kafka.common.ErrorMapping;
import kafka.common.OffsetAndMetadata;
import kafka.common.OffsetMetadataAndError;
import kafka.common.TopicAndPartition;
import kafka.javaapi.*;
import kafka.javaapi.consumer.SimpleConsumer;
import kafka.message.MessageAndOffset;
import kafka.network.BlockingChannel;
import java.nio.ByteBuffer;
import java.util.*;

/**
 * Low level consumer offical demo
 *
 * Low level consumer,used for:
 * Read a message multiple times
 * Consume only a subset of the partitions in a topic in a process
 * Manage transactions to make sure a message is processed once and only once
 * <p>
 * Downsides of use low level consumer:
 * You must keep track of the offsets in your application to know where you left off consuming.
 * You must figure out which Broker is the lead Broker for a topic and partition
 * You must handle Broker leader changes
 * <p>
 * Steps for using a SimpleConsumer:
 * Find an active Broker and find out which Broker is the leader for your topic and partition
 * Determine who the replica Brokers are for your topic and partition
 * Build the request defining what data you are interested in
 * Fetch the data
 * Identify and recover from leader changes
 * <p>
 * <p>
 * Committing and fetching consumer offsets in Kafka
 * https://cwiki.apache.org/confluence/display/KAFKA/Committing+and+fetching+consumer+offsets+in+Kafka
 *
 * @since 1.0
 */


public class LowLevelConsumerOfficalDemo {
    public static void main(String args[]) {
        LowLevelConsumerOfficalDemo example = new LowLevelConsumerOfficalDemo();
        long maxReads = Long.parseLong(args[0]);
        String topic = args[1];
        int partition = Integer.parseInt(args[2]);
        List<String> seeds = new ArrayList<String>();
        seeds.add(args[3]);
        int port = Integer.parseInt(args[4]);
        try {
            example.run(maxReads, topic, partition, seeds, port);
        } catch (Exception e) {
            System.out.println("Oops:" + e);
            e.printStackTrace();
        }
    }

    private List<String> m_replicaBrokers = new ArrayList<String>();

    public LowLevelConsumerOfficalDemo() {
        m_replicaBrokers = new ArrayList<String>();
    }

    /**
     * fetch message
     *
     * @param a_maxReads
     * @param a_topic
     * @param a_partition
     * @param a_seedBrokers
     * @param a_port
     * @throws Exception
     */
    public void run(long a_maxReads, String a_topic, int a_partition, List<String> a_seedBrokers, int a_port) throws Exception {
        // find the meta data about the topic and partition we are interested in
        //
        PartitionMetadata metadata = findLeader(a_seedBrokers, a_port, a_topic, a_partition);
        if (metadata == null) {
            System.out.println("Can't find metadata for Topic and Partition. Exiting");
            return;
        }
        if (metadata.leader() == null) {
            System.out.println("Can't find Leader for Topic and Partition. Exiting");
            return;
        }
        String leadBroker = metadata.leader().host();
        String clientName = "Client_" + a_topic + "_" + a_partition;

        SimpleConsumer consumer = new SimpleConsumer(leadBroker, a_port, 100000, 64 * 1024, clientName);
        long readOffset = getLastOffset(consumer, a_topic, a_partition, kafka.api.OffsetRequest.EarliestTime(), clientName);

        int numErrors = 0;
        while (a_maxReads > 0) {
            if (consumer == null) {
                consumer = new SimpleConsumer(leadBroker, a_port, 100000, 64 * 1024, clientName);
            }
            FetchRequest req = new FetchRequestBuilder()
                    .clientId(clientName)
                    .addFetch(a_topic, a_partition, readOffset, 100000) // Note: this fetchSize of 100000 might need to be increased if large batches are written to Kafka
                    .build();
            FetchResponse fetchResponse = consumer.fetch(req);

            if (fetchResponse.hasError()) {
                numErrors++;
                // Something went wrong!
                short code = fetchResponse.errorCode(a_topic, a_partition);
                System.out.println("Error fetching data from the Broker:" + leadBroker + " Reason: " + code);
                if (numErrors > 5) break;
                if (code == ErrorMapping.OffsetOutOfRangeCode()) {
                    // We asked for an invalid offset. For simple case ask for the last element to reset
                    readOffset = getLastOffset(consumer, a_topic, a_partition, kafka.api.OffsetRequest.LatestTime(), clientName);
                    continue;
                }
                consumer.close();
                consumer = null;
                leadBroker = findNewLeader(leadBroker, a_topic, a_partition, a_port);
                continue;
            }
            numErrors = 0;

            long numRead = 0;
            for (MessageAndOffset messageAndOffset : fetchResponse.messageSet(a_topic, a_partition)) {
                long currentOffset = messageAndOffset.offset();
                if (currentOffset < readOffset) {
                    System.out.println("Found an old offset: " + currentOffset + " Expecting: " + readOffset);
                    continue;
                }
                readOffset = messageAndOffset.nextOffset();
                ByteBuffer payload = messageAndOffset.message().payload();

                byte[] bytes = new byte[payload.limit()];
                payload.get(bytes);
                System.out.println(String.valueOf(messageAndOffset.offset()) + ": " + new String(bytes, "UTF-8"));
                numRead++;
                a_maxReads--;
            }
            if (numRead == 0) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ie) {
                }
            }
        }
        if (consumer != null) consumer.close();
    }

    /**
     * get last offset
     *
     * @param consumer
     * @param topic
     * @param partition
     * @param whichTime
     * @param clientName
     * @return
     */
    public static long getLastOffset(SimpleConsumer consumer, String topic, int partition,
                                     long whichTime, String clientName) {
        TopicAndPartition topicAndPartition = new TopicAndPartition(topic, partition);
        Map<TopicAndPartition, PartitionOffsetRequestInfo> requestInfo = new HashMap<TopicAndPartition, PartitionOffsetRequestInfo>();
        requestInfo.put(topicAndPartition, new PartitionOffsetRequestInfo(whichTime, 1));
        kafka.javaapi.OffsetRequest request = new kafka.javaapi.OffsetRequest(
                requestInfo, kafka.api.OffsetRequest.CurrentVersion(), clientName);
        OffsetResponse response = consumer.getOffsetsBefore(request);

        if (response.hasError()) {
            System.out.println("Error fetching data Offset Data the Broker. Reason: " + response.errorCode(topic, partition));
            return 0;
        }
        long[] offsets = response.offsets(topic, partition);
        return offsets[0];
    }

    /**
     * find new leader
     *
     * @param a_oldLeader
     * @param a_topic
     * @param a_partition
     * @param a_port
     * @return
     * @throws Exception
     */
    private String findNewLeader(String a_oldLeader, String a_topic, int a_partition, int a_port) throws Exception {
        for (int i = 0; i < 3; i++) {
            boolean goToSleep = false;
            PartitionMetadata metadata = findLeader(m_replicaBrokers, a_port, a_topic, a_partition);
            if (metadata == null) {
                goToSleep = true;
            } else if (metadata.leader() == null) {
                goToSleep = true;
            } else if (a_oldLeader.equalsIgnoreCase(metadata.leader().host()) && i == 0) {
                // first time through if the leader hasn't changed give ZooKeeper a second to recover
                // second time, assume the broker did recover before failover, or it was a non-Broker issue
                //
                goToSleep = true;
            } else {
                return metadata.leader().host();
            }
            if (goToSleep) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ie) {
                }
            }
        }
        System.out.println("Unable to find new leader after Broker failure. Exiting");
        throw new Exception("Unable to find new leader after Broker failure. Exiting");
    }

    /**
     * find leader broker
     *
     * @param a_seedBrokers
     * @param a_port
     * @param a_topic
     * @param a_partition
     * @return
     */
    private PartitionMetadata findLeader(List<String> a_seedBrokers, int a_port, String a_topic, int a_partition) {
        PartitionMetadata returnMetaData = null;
        loop:
        for (String seed : a_seedBrokers) {
            SimpleConsumer consumer = null;
            try {
                consumer = new SimpleConsumer(seed, a_port, 100000, 64 * 1024, "leaderLookup");
                List<String> topics = Collections.singletonList(a_topic);
                TopicMetadataRequest req = new TopicMetadataRequest(topics);
                kafka.javaapi.TopicMetadataResponse resp = consumer.send(req);

                List<TopicMetadata> metaData = resp.topicsMetadata();
                for (TopicMetadata item : metaData) {
                    for (PartitionMetadata part : item.partitionsMetadata()) {
                        if (part.partitionId() == a_partition) {
                            returnMetaData = part;
                            break loop;
                        }
                    }
                }
            } catch (Exception e) {
                System.out.println("Error communicating with Broker [" + seed + "] to find Leader for [" + a_topic
                        + ", " + a_partition + "] Reason: " + e);
            } finally {
                if (consumer != null) consumer.close();
            }
        }
        if (returnMetaData != null) {
            m_replicaBrokers.clear();
            for (kafka.cluster.Broker replica : returnMetaData.replicas()) {
                m_replicaBrokers.add(replica.host());
            }
        }
        return returnMetaData;
    }

    /**
     * save commit example,use BlockingChannel
     */
    public void saveCommit() {
        BlockingChannel channel = new BlockingChannel("localhost", 9092,
                BlockingChannel.UseDefaultBufferSize(),
                BlockingChannel.UseDefaultBufferSize(),
                5000 /* read timeout in millis */);
        channel.connect();
        final String MY_GROUP = "demoGroup";
        final String MY_CLIENTID = "demoClientId";
        int correlationId = 0;
        final TopicAndPartition testPartition0 = new TopicAndPartition("demoTopic", 0);
        final TopicAndPartition testPartition1 = new TopicAndPartition("demoTopic", 1);
        channel.send(new ConsumerMetadataRequest(MY_GROUP, ConsumerMetadataRequest.CurrentVersion(), correlationId++, MY_CLIENTID));
        ConsumerMetadataResponse metadataResponse = ConsumerMetadataResponse.readFrom(channel.receive().buffer());

        if (metadataResponse.errorCode() == ErrorMapping.NoError()) {
            Broker offsetManager = metadataResponse.coordinator();
            // if the coordinator is different, from the above channel's host then reconnect
            channel.disconnect();
            channel = new BlockingChannel(offsetManager.host(), offsetManager.port(),
                    BlockingChannel.UseDefaultBufferSize(),
                    BlockingChannel.UseDefaultBufferSize(),
                    5000 /* read timeout in millis */);
            channel.connect();
        } else {
            // retry (after backoff)
        }

        // How to commit offsets

        long now = System.currentTimeMillis();
        Map<TopicAndPartition, OffsetAndMetadata> offsets = new LinkedHashMap<>();
        offsets.put(testPartition0, new OffsetAndMetadata(100L, "associated metadata", now));
        offsets.put(testPartition1, new OffsetAndMetadata(200L, "more metadata", now));
        OffsetCommitRequest commitRequest = new OffsetCommitRequest(
                MY_GROUP,
                offsets,
                correlationId++,
                MY_CLIENTID,
                (short) 1 /* version */); // version 1 and above commit to Kafka, version 0 commits to ZooKeeper
        channel.send(commitRequest.underlying());
        OffsetCommitResponse commitResponse = OffsetCommitResponse.readFrom(channel.receive().buffer());
        if (commitResponse.hasError()) {
            for (Object partitionErrorCode : commitResponse.errors().values()) {
                Short partitionErrorCodeS = (Short) partitionErrorCode;
                if (partitionErrorCodeS == ErrorMapping.OffsetMetadataTooLargeCode()) {
                    // You must reduce the size of the metadata if you wish to retry
                } else if (partitionErrorCodeS == ErrorMapping.NotCoordinatorForConsumerCode() || partitionErrorCodeS == ErrorMapping.ConsumerCoordinatorNotAvailableCode()) {
                    channel.disconnect();
                    // Go to step 1 (offset manager has moved) and then retry the commit to the new offset manager
                } else {
                    // log and retry the commit
                }
            }
        }

        // How to fetch offsets
        List<TopicAndPartition> partitions = new ArrayList<>();
        partitions.add(testPartition0);
        OffsetFetchRequest fetchRequest = new OffsetFetchRequest(
                MY_GROUP,
                partitions,
                (short) 1 /* version */, // version 1 and above fetch from Kafka, version 0 fetches from ZooKeeper
                correlationId,
                MY_CLIENTID);
        channel.send(fetchRequest.underlying());
        OffsetFetchResponse fetchResponse = OffsetFetchResponse.readFrom(channel.receive().buffer());
        OffsetMetadataAndError result = fetchResponse.offsets().get(testPartition0);
        short offsetFetchErrorCode = result.error();
        if (offsetFetchErrorCode == ErrorMapping.NotCoordinatorForConsumerCode()) {
            channel.disconnect();
            // Go to step 1 and retry the offset fetch
        } else if (offsetFetchErrorCode == ErrorMapping.OffsetsLoadInProgressCode()) {
            // retry the offset fetch (after backoff)
        } else {
//            long retrievedOffset = result.offset();
//            String retrievedMetadata = result.metadata();
        }

    }
}
