package com.atjl.util.guava;


import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutionException;

public class CacheTest {


    public static void main(String[] args) {
        initLoadingCache();
        try {
            Man m = loadingCache.get("123");
            System.out.println("get1 " + m);
            m = loadingCache.get("234");
            System.out.println("get2 " + m);
            m = loadingCache.get("123");
            System.out.println("get3 " + m);
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }


    private static LoadingCache<String, Man> loadingCache;

    //loadingCache
    public static void initLoadingCache() {
        //指定一个如果数据不存在获取数据的方法
        CacheLoader<String, Man> cacheLoader = new CacheLoader<String, Man>() {
            @Override
            public Man load(String key) throws Exception {
                //模拟mysql操作
                Logger logger = LoggerFactory.getLogger("LoadingCache");
                logger.info("LoadingCache测试 从mysql加载缓存ing...(2s)");
                Thread.sleep(1000);
                logger.info("LoadingCache测试 从mysql加载缓存成功");
                Man tmpman = new Man();
                tmpman.setId(key);
                tmpman.setName("其他人");
                if (key.equals("001")) {
                    tmpman.setName("张三");
                    return tmpman;
                }
                if (key.equals("002")) {
                    tmpman.setName("李四");
                    return tmpman;
                }
                return tmpman;
            }
        };
        //缓存数量为1，为了展示缓存删除效果
        loadingCache = CacheBuilder.newBuilder().maximumSize(2).build(cacheLoader);
    }

    //获取数据，如果不存在返回null
    public static Man getIfPresentloadingCache(String key) {
        return loadingCache.getIfPresent(key);
    }

    //获取数据，如果数据不存在则通过cacheLoader获取数据，缓存并返回
    public static Man getCacheKeyloadingCache(String key) {
        try {
            return loadingCache.get(key);
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    //直接向缓存put数据
    public static void putloadingCache(String key, Man value) {
        Logger logger = LoggerFactory.getLogger("LoadingCache");
        logger.info("put key :{} value : {}", key, value.getName());
        loadingCache.put(key, value);
    }


    private static class Man {
        //身份证号
        private String id;
        //姓名
        private String name;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Man{" +
                    "id='" + id + '\'' +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

}
