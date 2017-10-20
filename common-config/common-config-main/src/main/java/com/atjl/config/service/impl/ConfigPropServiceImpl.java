package com.atjl.config.service.impl;

import com.atjl.config.api.ConfigConstant;
import com.atjl.config.api.ConfigDbConstant;
import com.atjl.config.api.ConfigService;
import com.atjl.util.config.ConfigPropUtil;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 通过 配置文件读取
 */
@Component(ConfigConstant.CONF_SERVICE_PROP)
public class ConfigPropServiceImpl implements ConfigService {

    @Override
    @CacheEvict(value = ConfigDbConstant.CONFIG_CACHE, key = "#pathKey")
    @Transactional
    public String get(String key) {
        return ConfigPropUtil.get(key);
    }

    @Override
    public String getNoCache(String key) {
        return ConfigPropUtil.get(key);
    }

    @Override
    @Cacheable(value = ConfigDbConstant.CONFIG_PROP_CACHE_MK, key = "#keys.toString()")
    public Map<String, String> getBatch(List<String> keys) {
        return ConfigPropUtil.getBatch(keys);
    }

    @Override
    public Map<String, String> getBatchNoCache(List<String> keys) {
        return ConfigPropUtil.getBatch(keys);
    }

    @Override
    public boolean set(String key, String v) {
        //do nothing
        return true;
    }
}
