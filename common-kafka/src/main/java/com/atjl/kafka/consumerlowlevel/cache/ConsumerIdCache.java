package com.atjl.kafka.consumerlowlevel.cache;

import com.atjl.common.api.ICache;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class ConsumerIdCache implements ICache<String> {
    /**
     * K:topic
     * V:consumerid
     */
    private static Map<String, String> cache = new ConcurrentHashMap<>();

    @Override
    public String get(String topic) {
        return cache.get(topic);
    }

    @Override
    public boolean contain(String key) {
        return cache.containsKey(key);
    }

    @Override
    public String remove(String key) {
        return cache.remove(key);
    }

    @Override
    public void put(String topic, String consumerid) {
        cache.put(topic, consumerid);
    }

    @Override
    public void init() {

    }

    @Override
    @PreDestroy
    public void destroy() {
        if (cache != null) {
            cache.clear();
        }
        cache = null;
    }

    @Override
    public String getStatus() {
        return null;
    }

}
