package com.atjl.cache.memcache;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.MemcachedClientBuilder;
import net.rubyeye.xmemcached.XMemcachedClientBuilder;
import net.rubyeye.xmemcached.exception.MemcachedException;
import net.rubyeye.xmemcached.utils.AddrUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * memcache helper
 */
public final class MemcacheUtil {

    public static final Logger logger = LoggerFactory.getLogger(MemcacheUtil.class);
    /**
     * 定义MemcachedConfiguration缓存配置信息
     */
    private MemcachedConfiguration configuration;
    /**
     * 定义MemcachedClientBuilder
     */
    private MemcachedClientBuilder builder = null;
    /**
     * 定义MemcachedClient
     */
    private MemcachedClient client = null;

    private MemcacheUtil() {
    }

    /**
     * 初始化配置信息
     */
    private void init() {
        builder = new XMemcachedClientBuilder(AddrUtil.getAddresses(configuration.getAddr() + ":" + configuration.getPort()));
        // 宕机报警
        builder.setFailureMode(configuration.getFailureMode());
        // 使用二进制文件
        builder.setCommandFactory(configuration.getCommandFactory());
        // 连接池大小
        builder.setConnectionPoolSize(configuration.getPoolSize());
        try {
            client = builder.build();
            client.setEnableHeartBeat(false);
        } catch (IOException e) {
            logger.error("init memcached error : ", e);
        }
    }

    /**
     * 类级的内部类，也就是静态的成员式内部类，该内部类的实例与外部类的实例没有绑定关系， 而且只有被调用到才会装载，从而实现了延迟加载
     */
    private static class SingletonHolder {
        /**
         * 静态初始化器，由JVM来保证线程安全
         */
        private static MemcacheUtil instance = new MemcacheUtil();
    }

    /**
     * 得到新实例
     */
    public static MemcacheUtil newInstance(MemcachedConfiguration configuration) {
        MemcacheUtil helper = SingletonHolder.instance;
        if (helper.configuration != null) {
            return helper;
        }
        helper.configuration = configuration;
        helper.init();
        return helper;
    }

    /**
     * 将数据添加置Memcached
     *
     * @param key   {@link String} 主键Key
     * @param value {@link Object} 添加的数据
     * @throws TimeoutException
     * @throws InterruptedException
     * @throws MemcachedException
     */
    public void set(String key, Object value) throws TimeoutException, InterruptedException, MemcachedException {
        logger.debug("key = " + key + ",value = " + String.valueOf(value));
        client.set(key, configuration.getEffectiveTime(), value);
    }

    /**
     * 根据key获取value
     *
     * @param key {@link String}
     * @throws TimeoutException
     * @throws InterruptedException
     * @throws MemcachedException
     */
    public Object get(String key) throws TimeoutException, InterruptedException, MemcachedException {
        logger.debug("key = " + key);
        return client.get(key);
    }

    /**
     * 从Memcached删除数据
     *
     * @param key {@link String}
     * @return {@link Boolean}
     * @throws TimeoutException
     * @throws InterruptedException
     * @throws MemcachedException
     */
    public boolean delete(String key) throws TimeoutException, InterruptedException, MemcachedException {
        logger.debug("key = " + key);
        return client.delete(key);
    }
}
