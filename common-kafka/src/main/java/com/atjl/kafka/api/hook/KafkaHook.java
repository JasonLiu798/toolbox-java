package com.atjl.kafka.api.hook;

/**
 * 消费者用，默认实现见
 * DefaultKafkaHook
 */
public interface KafkaHook {
    boolean before(String type, String topic, String cid);
    void after(String type, String topic, String cid);
}
