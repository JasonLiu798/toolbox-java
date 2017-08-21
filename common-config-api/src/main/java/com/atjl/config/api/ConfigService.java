package com.atjl.config.api;

import java.util.List;
import java.util.Map;

/**
 * 通用 配置 读取接口
 */
public interface ConfigService {
    /**
     * 设值
     *
     * @param key
     * @param v
     */
    boolean set(String key, String v);

    /**
     * 获取一个
     *
     * @param key
     * @return
     */
    String get(String key);

    /**
     * 获取一个
     *
     * @param key
     * @return
     */
    String getNoCache(String key);

    /**
     * 获取多个
     *
     * @param key
     * @return
     */
    Map<String, String> gets(String key);

    /**
     * 获取多个
     *
     * @return
     */
    Map<String, String> getBatch(List<String> keys);

    /**
     * 获取多个，无缓存
     * @param keys
     * @return
     */
    Map<String, String> getBatchNoCache(List<String> keys);


}
