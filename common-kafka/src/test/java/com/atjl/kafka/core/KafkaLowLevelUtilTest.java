package com.atjl.kafka.core;

import com.atjl.kafka.domain.Broker;
import com.atjl.kafka.api.event.Event;
import kafka.javaapi.consumer.SimpleConsumer;
import kafka.javaapi.message.ByteBufferMessageSet;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import java.io.IOException;
import java.util.*;


/**
 * KafkaLowLevelUtil
 */
public class KafkaLowLevelUtilTest {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: getZkUrl(String clusterName, String token, String systemid, String monitorUrl)
     */
    @Test
    public void testGetZk() throws Exception {
        //kafka.producer.url=http://10.202.34.30:8292/testc
//        kafka.producer.clusterName=other
//        kafka.producer.topicTokens=A1:1!t2joIG,A2:@QCmv$f2
        /**
         * kafka.consumer.url=http://10.202.34.30:8292/testc
         kafka.consumer.clusterName=other
         kafka.consumer.systemIdToken=A1C:8N^5$9sl
         */

        String url = "http://10.202.34.30:8292/testc";
        String clusterName = "other";
        String systemId = "A1C";
        String token = "8N^5$9sl";

        //topicToken = "test:666666";
        url = "http://10.202.30.7/testc";
        systemId = "testc";
        token = "OyI1u8^B";

        //    public static String getZkUrl(String clusterName,String token,String systemid,String monitorUrl){
        String zk = KafkaLowLevelUtil.getZk(clusterName, token, systemId, url);
        System.out.println(zk);
    }

    /**
     * Method: getBrokers(String clusterName, String topicToken, String monitorUrl)
     */
    @Test
    public void testGetBrokers() throws Exception {
        String clusterName = "other";
        String topicToken = "A1:1!t2joIG,";
        String url = "http://10.202.34.30:8292/testc";
        //    public static String getBrokers(String clusterName,String topicToken,String monitorUrl){
        List<Broker> brokers = KafkaLowLevelUtil.getBrokers(clusterName, topicToken, url);
        System.out.println(brokers);
    }

    /**
     * Method: getLeaderBroker(List<String> seedBrokers, int port, String topic, int partition, List<String> replicaBrokers)
     */
    @Test
    public void testGetLeaderBroker() throws Exception {
        String clusterName = "other";
        String topicToken = "A1:1!t2joIG,";
        String url = "http://10.202.34.30:8292/testc";
        //    public static String getBrokers(String clusterName,String topicToken,String monitorUrl){
        List<Broker> brokers = KafkaLowLevelUtil.getBrokers(clusterName, topicToken, url);
        String topic = "A1";
//    public static Broker getLeaderBroker(List<Broker> seedBrokers,String topic,int partition,List<Broker> replicaBrokers){
        List<Broker> replist = new LinkedList<>();
        Broker broker = KafkaLowLevelUtil.getLeaderBroker(brokers, topic, 0);
        System.out.println("leader:" + broker);
        System.out.println("replica:" + replist);
    }

    /**
     * Method: getSimpleConsumer(String leaderBroker, int port, String name)
     */
    @Test
    public void testGetSimpleConsumer() throws Exception {
        String clusterName = "other";
        String topicToken = "A1:1!t2joIG,";
        String url = "http://10.202.34.30:8292/testc";
        //    public static String getBrokers(String clusterName,String topicToken,String monitorUrl){
        List<Broker> brokers = KafkaLowLevelUtil.getBrokers(clusterName, topicToken, url);
        String topic = "A1";
//    public static Broker getLeaderBroker(List<Broker> seedBrokers,String topic,int partition,List<Broker> replicaBrokers){
        Broker broker = KafkaLowLevelUtil.getLeaderBroker(brokers, topic, 0);
        SimpleConsumer sc = KafkaLowLevelUtil.getSimpleConsumer(broker, "s1");
        System.out.println("simple consumer:" + sc);
    }


    /**
     * Method: fetchMessages(LowLevelConsumerConfig config, SimpleConsumer consumer, Partition partition, long offset)
     */
    @Test
    public void testFetchMessages() throws Exception {
        String clusterName = "other";
        String topicToken = "A1:1!t2joIG,";
        String url = "http://10.202.34.30:8292/testc";
        //    public static String getBrokers(String clusterName,String topicToken,String monitorUrl){
        List<Broker> brokers = KafkaLowLevelUtil.getBrokers(clusterName, topicToken, url);
        String topic = "A1";
//    public static Broker getLeaderBroker(List<Broker> seedBrokers,String topic,int partition,List<Broker> replicaBrokers){
        Broker broker = KafkaLowLevelUtil.getLeaderBroker(brokers, topic, 0);
        SimpleConsumer sc = KafkaLowLevelUtil.getSimpleConsumer(broker, "s1");

        long offset = KafkaLowLevelUtil.getProducedOffset(sc, "A1", 0);
        System.out.println("offset:" + offset);

        ByteBufferMessageSet msg = KafkaLowLevelUtil.fetchMessagesInner(sc, "A1", 0, offset - 1, 10);
        System.out.println("get msg:" + msg);
    }

    @Test
    public void testParse() throws Exception {
        String clusterName = "other";
        String topicToken = "A1:1!t2joIG,";
        String url = "http://10.202.34.30:8292/testc";
        //    public static String getBrokers(String clusterName,String topicToken,String monitorUrl){
        List<Broker> brokers = KafkaLowLevelUtil.getBrokers(clusterName, topicToken, url);
        String topic = "A1";
//    public static Broker getLeaderBroker(List<Broker> seedBrokers,String topic,int partition,List<Broker> replicaBrokers){

        Broker broker = KafkaLowLevelUtil.getLeaderBroker(brokers, topic, 0);
        SimpleConsumer sc = KafkaLowLevelUtil.getSimpleConsumer(broker, "s1");

        long offset = KafkaLowLevelUtil.getProducedOffset(sc, "A1", 0);
        System.out.println("offset:" + offset);
        long startOffset = offset - 100 + 1;
        ByteBufferMessageSet msg = KafkaLowLevelUtil.fetchMessagesInner(sc, "A1", 0, startOffset, 2235);
        List<Event> res = KafkaLowLevelUtil.parseMessageSet2Event(msg);
        System.out.println("get msg:" + res);
        /**
         *
         * offset 55~66  fetch  925
         * 66-55+1 = 12
         * 925/12 = 77.08
         * 925+77.08 = 1002
         *
         * 925+77.08*5 = 2235.4
         * 67
         * 84-55+1 = 30
         * 74.5
         *
         *
         *
         * 1000/13 = 76.92
         * 1000/12 = 83
         * 1000/14 = 71
         * 71~83
         * 71*13 = 923  [of 55~66]
         *
         *
         */
    }

    @Test
    public void testGetOffsetBf() {
        String topic = "test";
        String groupid = "testc";

        String topicToken = "test:666666";
        String url = "http://10.202.30.7/testc";
        String clusterName = "other";

        //ResetOffsetOperator.resetOffset(topic, group, null, whichTime);
        List<Broker> brokers = KafkaLowLevelUtil.getBrokers(clusterName, topicToken, url);
        Broker broker = KafkaLowLevelUtil.getLeaderBroker(brokers, topic, 0);
        SimpleConsumer sc = KafkaLowLevelUtil.getSimpleConsumer(broker, groupid);
        //long ts = DateUtil.getNowTS();
        //DateUtil.getDateTS(-3);
        //long st = ts;
        long offset = KafkaLowLevelUtil.getOffsetBefore(sc, topic, 0, -2);
        System.out.println(offset);
        /*
        StringBuilder sb = new StringBuilder();
        Map set = new HashMap<>();
        for(int i=0;i<10000;i++) {
            st = (ts - i) * 1000;
            //System.out.println("ts:" + st);
            long offset = KafkaLowLevelUtil.getOffsetBefore(sc, topic, 0, st);
            set.put(offset,i);
        }
        for(Object offset:set.keySet()) {
            sb.append("num:").append(offset).append(",offset:").append(set.get(offset)).append("\n");
        }
        System.out.println(sb.toString());
        */
    }

    @Test
    public void testGetLastOffset() throws Exception {
        String clusterName = "other";
        String topicToken = "A1:1!t2joIG,";
        String url = "http://10.202.34.30:8292/testc";

        topicToken = "test:666666";
        url = "http://10.202.30.7/testc";
        String groupid = "testc";
        //    public static String getBrokers(String clusterName,String topicToken,String monitorUrl){
        List<Broker> brokers = KafkaLowLevelUtil.getBrokers(clusterName, topicToken, url);
        String topic = "test";
//    public static Broker getLeaderBroker(List<Broker> seedBrokers,String topic,int partition,List<Broker> replicaBrokers){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            Broker broker = KafkaLowLevelUtil.getLeaderBroker(brokers, topic, i);
            SimpleConsumer sc = KafkaLowLevelUtil.getSimpleConsumer(broker, groupid);
            long offset = KafkaLowLevelUtil.getProducedOffset(sc, topic, i);
            sb.append("partition: " + i + ",offset:" + offset).append("\n");
        }
        System.out.println(sb);
        /*
        partition: 0,offset:340
        partition: 1,offset:328
        partition: 2,offset:247
        partition: 3,offset:185
         */
    }

    @Test
    public void testGetCurrentOffset() throws Exception {
        //getProducedOffset
        String clusterName = "other";
        String topicToken = "A1:1!t2joIG,";
        String url = "http://10.202.34.30:8292/testc";


        topicToken = "test:666666";
        url = "http://10.202.30.7/testc";

        //    public static String getBrokers(String clusterName,String topicToken,String monitorUrl){
        List<Broker> brokers = KafkaLowLevelUtil.getBrokers(clusterName, topicToken, url);
        String topic = "test";
        String groupid = "testc";
//    public static Broker getLeaderBroker(List<Broker> seedBrokers,String topic,int partition,List<Broker> replicaBrokers){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            Broker broker = KafkaLowLevelUtil.getLeaderBroker(brokers, topic, i);
            SimpleConsumer sc = KafkaLowLevelUtil.getSimpleConsumer(broker, groupid);
            long offset = KafkaLowLevelUtil.getCommitedOffset(sc, topic, i);
            sb.append("partition: " + i + ",offset:" + offset).append("\n");
        }
        System.out.println(sb);
    }


    @Test
    public void testSetCommitUseZk() throws IOException {
        String zk = "10.202.34.31:2181,10.202.34.33:2181,10.202.34.34:2181/kafka/other";
        String topic = "test";
        String groupid = "testc";

        String topicToken = "test:666666";
        String url = "http://10.202.30.7/testc";
        String clusterName = "other";

        //ResetOffsetOperator.resetOffset(topic, group, null, whichTime);
        List<Broker> brokers = KafkaLowLevelUtil.getBrokers(clusterName, topicToken, url);

        ZookeeperOperator zookeeperOperator = new ZookeeperOperator(zk);
        for (int i = 0; i < 4; i++) {
            Broker broker = KafkaLowLevelUtil.getLeaderBroker(brokers, topic, i);
            SimpleConsumer sc = KafkaLowLevelUtil.getSimpleConsumer(broker, "testc");
            long offset = KafkaLowLevelUtil.getProducedOffset(sc, topic, i);

            zookeeperOperator.setTopicGroupOffset(topic, groupid, "" + i, "" + (offset - 100));
        }
    }

    @Test
    public void testSetCommitUseSimpleConsumer() {
        String topic = "test";
        String groupid = "testc";

        String topicToken = "test:666666";
        String url = "http://10.202.30.7/testc";
        String clusterName = "other";

        //ResetOffsetOperator.resetOffset(topic, group, null, whichTime);
        List<Broker> brokers = KafkaLowLevelUtil.getBrokers(clusterName, topicToken, url);
        for (int i = 0; i < 4; i++) {
            Broker broker = KafkaLowLevelUtil.getLeaderBroker(brokers, topic, i);
            SimpleConsumer sc = KafkaLowLevelUtil.getSimpleConsumer(broker, groupid);
            //long offset = KafkaLowLevelUtil.getCommitedOffset(sc, topic, i);
            KafkaLowLevelUtil.commitOffset(sc, topic, i, 1);//offset - 50);
        }
    }


}
