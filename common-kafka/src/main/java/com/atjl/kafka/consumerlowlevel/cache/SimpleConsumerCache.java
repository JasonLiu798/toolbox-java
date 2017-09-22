package com.atjl.kafka.consumerlowlevel.cache;

import com.atjl.common.api.ICache;
import kafka.javaapi.consumer.SimpleConsumer;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class SimpleConsumerCache implements ICache<SimpleConsumer> {
    private static Map<String,SimpleConsumer> simpleConsumerMap;

    public static String formatKey(String groupid, String topic, int partition){
        return String.format("%s-%s-%s",groupid,topic,partition);
    }

    @Override
    public void put(String key, SimpleConsumer sc) {
        simpleConsumerMap.put(key,sc);
    }

    @Override
    public SimpleConsumer get(String key) {
        return simpleConsumerMap.get(key);
    }

    @Override
    public boolean contain(String key) {
        return simpleConsumerMap.containsKey(key);
    }

    @Override
    public SimpleConsumer remove(String key) {
        return simpleConsumerMap.remove(key);
    }

    @Override
    @PostConstruct
    public void init() {
        simpleConsumerMap = new ConcurrentHashMap<>();
    }

    @Override
    @PreDestroy
    public void destroy() {
        for(Map.Entry<String,SimpleConsumer> entry: simpleConsumerMap.entrySet()){
            SimpleConsumer sc = entry.getValue();
            sc.close();
        }
    }

    @Override
    public String getStatus() {
        StringBuilder sb = new StringBuilder();
        sb.append(SimpleConsumerCache.class).append(":[").append(simpleConsumerMap).append("]");
        return sb.toString();
    }

//    public static ZkService getZkByUrl(String url){
//    }

}
