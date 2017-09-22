package com.atjl.kafka.consumer;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.atjl.util.config.ConfigPropConstant;
import com.atjl.util.config.util.ConfigLoadUtil;
import com.atjl.kafka.domain.constant.KafkaConfigConstant;

import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;
import kafka.serializer.StringDecoder;
import kafka.utils.VerifiableProperties;

/**
 * 单线程 消费者
 *
 * @since 1.0
 */
public class MQConsumerST {

    private final ConsumerConnector consumer;

    private MQConsumerST() {
        Properties props = null;
        try {
            props = ConfigLoadUtil.loadProperties(KafkaConfigConstant.DFT_CONF_FILE, ConfigPropConstant.TP_CLASSPATH);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //TODO: possible null pointer deference
        //zookeeper 配置
//        props.put("zookeeper.connect", props.get("zookeeper.connect"));

        //group 代表一个消费组
        props.put("group.id", "jd-group");

        //zk连接超时
        props.put("zookeeper.session.timeout.ms", props.get("zookeeper.session.timeout.ms"));
        props.put("zookeeper.sync.time.ms",  props.get("zookeeper.sync.time.ms"));
        props.put("auto.commit.interval.ms",  props.get("auto.commit.interval.ms"));
        props.put("auto.offset.reset",  props.get("auto.offset.reset"));
        //序列化类
        props.put("serializer.class", props.get("serializer.class"));

        ConsumerConfig config = new ConsumerConfig(props);

        consumer = kafka.consumer.Consumer.createJavaConsumerConnector(config);
    }

    void consume(String topic) {
        Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
        topicCountMap.put(topic, new Integer(1));

        StringDecoder keyDecoder = new StringDecoder(new VerifiableProperties());
        StringDecoder valueDecoder = new StringDecoder(new VerifiableProperties());

        Map<String, List<KafkaStream<String, String>>> consumerMap = 
                consumer.createMessageStreams(topicCountMap,keyDecoder,valueDecoder);
        KafkaStream<String, String> stream = consumerMap.get(topic).get(0);
        ConsumerIterator<String, String> it = stream.iterator();
        while (it.hasNext())
            System.out.println(it.next().message());
    }

    public static void main(String[] args) {
        new MQConsumerST().consume("AA");
    }
}