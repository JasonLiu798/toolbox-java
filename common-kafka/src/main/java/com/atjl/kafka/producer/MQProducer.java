package com.atjl.kafka.producer;

import com.atjl.kafka.api.config.ProducerConfig;
import com.atjl.kafka.api.event.Event;
import com.atjl.kafka.domain.constant.KafkaConfigConstant;

import org.apache.http.annotation.ThreadSafe;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * Kafka 生产者，使用新版API
 *
 * @since 1.0
 */
@ThreadSafe
public class MQProducer {

    private static Logger LOG = LoggerFactory.getLogger(MQProducer.class);

    /**
     * kafaka producer
     */
    private Producer<String, String> producer;

    /**
     * config file
     */
    private String propertiesFile;
    /**
     * config properties
     */
    private Properties properties;

    public MQProducer() {
        this.propertiesFile = KafkaConfigConstant.DFT_CONF_FILE;
        init();
    }

    public MQProducer(String propertiesFile) {
        this.propertiesFile = propertiesFile;
        init();
    }

    /**
     * inti method ,create producer from config file or properties
     */
    private void init() {
        if (properties == null && propertiesFile == null) {
            throw new IllegalArgumentException("The properties object or file can't be null.");
        }
        //if (properties == null) {
        //    properties = ConfigUtil.loadProperties(propertiesFile);
        //}
        ProducerConfig pconf = null;//ConfigUtil.generateConfigModel(ProducerConfig.class,KafkaConfigConstant.PCONF_PREFIX,this.propertiesFile,null );

        Properties properties = pconf.createProducerConfig();
        properties.put("value.serializer", StringSerializer.class);
        properties.put("key.serializer", StringSerializer.class);
        if (LOG.isDebugEnabled()) {
            LOG.debug("init producer with properteis {}", properties);
        }
        producer = new KafkaProducer<>(properties);
        LOG.info("kafka producer is started.");
    }

    /**
     * send data
     *
     * @param topic topic
     * @param key   key
     * @param value value
     */
    public void send(String topic, String key, String value) {
        ProducerRecord<String, String> record = new ProducerRecord<>(topic, key, value);
        producer.send(record);
        if (LOG.isDebugEnabled()) {
            LOG.debug("send to topic {} key {},value {}", topic, key, value);
        }
    }

    /**
     * send data
     *
     * @param event event
     *              void
     */
    public void send(Event event) {
        if (event == null || event.getTopic() == null) {
            throw new IllegalArgumentException("");
        }
        send(event.getTopic(), event.getKey(), event.getValue());
    }

    public void close() {
        producer.close();
    }

}
