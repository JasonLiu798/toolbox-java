package com.atjl.config.service;

import com.atjl.config.api.ConfigConstant;
import com.atjl.config.api.ConfigService;
import com.atjl.config.util.ConfigDftValUtil;
import com.atjl.configdb.api.ConfigDbConstant;
import com.atjl.configdb.api.ConfigDbService;
import com.atjl.util.character.StringCheckUtil;
import com.atjl.util.collection.CollectionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 配置 服务，数据字典方式实现
 */
@Component(ConfigConstant.CONF_SERVICE_USE_DICT)
public class ConfigServiceUseDictImpl implements ConfigService {
    private static final Logger logger = LoggerFactory.getLogger(ConfigServiceUseDictImpl.class);

    @Resource(name = ConfigDbConstant.CONF_DB_SERVICE)
    private ConfigDbService configDbService;

    /**
     * 获取 long
     *
     * @param key
     * @param dftVal
     * @return
     */
    @Override
    public Long getLongVal(String key, Long dftVal) {
        Long v = getLongVal(key);
        return v == null ? dftVal : v;
    }

    @Override
    public Long getLongVal(String key) {
        String v = configDbService.get(key);
        if (StringCheckUtil.isEmpty(v)) {
            return null;
        }
        try {
            return Long.parseLong(v);
        } catch (Exception e) {
            logger.error("str to long fail,{}", e.getMessage());
            return null;
        }
    }

    /**
     * 获取 int
     *
     * @param key
     * @param dftVal
     * @return
     */
    @Override
    public Integer getIntVal(String key, Integer dftVal) {
        Integer v = getIntVal(key);
        return v == null ? dftVal : v;
    }

    @Override
    public Integer getIntVal(String key) {
        String v = configDbService.get(key);
        if (StringCheckUtil.isEmpty(v)) {
            return null;
        }
        try {
            return Integer.parseInt(v);
        } catch (Exception e) {
            logger.error("str to int fail,{}", e.getMessage());
            return null;
        }
    }

    /**
     * 获取 string
     *
     * @param key
     * @param dftVal
     * @return
     */
    @Override
    public String getStrValue(String key, String dftVal) {
        String v = configDbService.get(key);
        return StringCheckUtil.isEmpty(v) ? dftVal : v;
    }

    @Override
    public String getStrValue(String key) {
        return null;
    }

    /**
     *
     * @param key
     * @param dft
     * @return
     */
    @Override
    public String[] getStrArrayValue(String key, String sep, String[] dft) {
        Map<String, String> res = configDbService.gets(key);
        if (CollectionUtil.isEmpty(res)) {
            return dft;
        }
        return null;
    }

    /**
     * 获取参数
     * @param keys
     * @return
     */
    @Override
    public Map<String, String> getStrValueBatch(List<String> keys) {
        if (CollectionUtil.isEmpty(keys)) {
            return new HashMap<>();
        }
        return configDbService.getBatch(keys);
    }

    /**
     * 批量 获取 kv
     *
     * @param keyAndDftVal
     * @return
     */
    @Override
    public Map<String, String> getStrValueBatch(Map<String, String> keyAndDftVal) {
        Map<String, String> kvs = new HashMap<>();
        if (CollectionUtil.isEmpty(keyAndDftVal)) {
            return kvs;
        }
        List<String> keys = new ArrayList<>();
        keys.addAll(keyAndDftVal.keySet());
        kvs = configDbService.getBatch(CollectionUtil.map2list(keyAndDftVal, true));
        return ConfigDftValUtil.rawFilterAddDft(kvs, keyAndDftVal);
    }


}
