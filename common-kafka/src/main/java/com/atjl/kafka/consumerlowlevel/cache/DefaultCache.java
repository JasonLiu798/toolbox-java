package com.atjl.kafka.consumerlowlevel.cache;

import com.atjl.common.api.ICache;

import javax.annotation.PreDestroy;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class DefaultCache implements ICache<Object> {
    private static Map<String,Object> cache = new ConcurrentHashMap<>();

    public static Map<String,Object> getAll(){
        return cache;
    }

    public void restart() {
        cache = new ConcurrentHashMap<>();
    }

    @Override
    public void put(String key, Object data) {
        cache.put(key,data);
    }

    @Override
    public Object get(String topic){
        return cache.get(topic);
    }

    @Override
    @PreDestroy
    public void destroy() {
        if(cache!=null){
            cache.clear();
        }
        cache = null;
    }

}
