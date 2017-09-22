package com.atjl.kafka.consumerlowlevel.cache;

import com.atjl.common.api.ICache;
import com.atjl.kafka.domain.Broker;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class BrokerCache implements ICache<List<Broker>> {
    private static Map<String,List<Broker>> brokerListMap = new ConcurrentHashMap<>();

    public static Map<String,List<Broker>> getAll(){
        return brokerListMap;
    }

    @Override
    public void put(String topic, List<Broker> list) {
        brokerListMap.put(topic,list);
    }

    @Override
    public List<Broker> get(String  topic){
        return brokerListMap.get(topic);
    }

    @Override
    public boolean contain(String key) {
        return brokerListMap.containsKey(key);
    }

    @Override
    public List<Broker> remove(String key) {
        return brokerListMap.remove(key);
    }

    @Override
    public void init() {

    }

    @Override
    @PreDestroy
    public void destroy() {
        if(brokerListMap!=null) {
            brokerListMap.clear();
        }
        brokerListMap = null;
    }

    @Override
    public String getStatus() {
        StringBuilder sb = new StringBuilder();
        sb.append(BrokerCache.class).append("[").append(brokerListMap).append("]");
        return sb.toString();
    }


}
