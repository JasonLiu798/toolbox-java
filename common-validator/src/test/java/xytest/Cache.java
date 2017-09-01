package xytest;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by liujianlong on 16/3/30.
 */
public class Cache {

    private static ConcurrentHashMap<String,Object> flows=new ConcurrentHashMap<>();

    public static void putObj(String key,Object o){
        flows.put(key,o);
    }

    public static Object getObj(String key){
        return flows.get(key);
    }

}
