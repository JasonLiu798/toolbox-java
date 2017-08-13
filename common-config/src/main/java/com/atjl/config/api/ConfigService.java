package com.atjl.config.api;

import java.util.List;
import java.util.Map;

/**
 * 通用 配置 读取接口
 */
public interface ConfigService {

    /**
     * 获取 long
     *
     * @param key
     * @param dftVal
     * @return
     */
    Long getLongVal(String key, Long dftVal);
    Long getLongVal(String key);

    /**
     * 获取 int,不存在或异常返回dftVal
     *
     * @param key
     * @param dftVal
     * @return
     */
    Integer getIntVal(String key, Integer dftVal);
    Integer getIntVal(String key);

    /**
     * 获取string
     *
     * @param key
     * @param dftVal
     * @return
     */
    String getStrValue(String key, String dftVal);
    String getStrValue(String key);

    /**
     * 获取 string 数组
     *
     * @param key
     * @return
     */
    String[] getStrArrayValue(String key, String sep, String[] dftVal);


    /**
     * 批量获取 string ，不含默认值
     * @param keys
     * @return
     */
    Map<String, String> getStrValueBatch(List<String> keys);
    /**
     * 获取 string 批量，包含 默认值
     *
     * @param keyAndDftVal
     * @return
     */
    Map<String, String> getStrValueBatch(Map<String, String> keyAndDftVal);


}
