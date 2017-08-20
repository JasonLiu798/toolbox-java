package com.atjl.configdb.service.impl;

import com.atjl.configdb.api.ConfigDbConstant;
import com.atjl.configdb.api.ConfigDbService;
import com.atjl.configdb2.mapper.ConfigTreeMapper;
import com.atjl.util.collection.CollectionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 库配置读取
 */
@Component(ConfigDbConstant.CONF_DB_PLAIN_SERVICE)
public class ConfigDbPlainServiceImpl implements ConfigDbService {
    private static final Logger logger = LoggerFactory.getLogger(ConfigDbPlainServiceImpl.class);
    @Resource
    private ConfigTreeMapper configDbDao;
    @Resource
    EhCacheCacheManager ehCacheCacheManager;

    /**
     * 设置值
     * todo: 多机未做同步，同步间隔 120s
     *
     * @return
     */
    @Override
    @CacheEvict(value = ConfigDbConstant.CONFIG_CACHE, key = "#pathKey")
    @Transactional
    public boolean set(String pathKey, String v) {
        try {
            ehCacheCacheManager.getCacheManager().getCache(ConfigDbConstant.CONFIG_CACHE_M).removeAll();//强制失效 CONFIG_CACHE_M
            ehCacheCacheManager.getCacheManager().getCache(ConfigDbConstant.CONFIG_CACHE_MK).removeAll();//强制失效 CONFIG_CACHE_MK
        } catch (Exception e) {
            logger.error("clear cache CONFIG_M,CONFIG_MK error {}", e.getMessage());
        }
        return configDbDao.set(pathKey, v) == 1;
    }

    /**
     * 获取单个值
     *
     * @param pathKey
     * @return
     */
    @Override
    @Cacheable(value = ConfigDbConstant.CONFIG_CACHE, key = "#pathKey")
    public String get(String pathKey) {
        return configDbDao.get(pathKey);
    }

    /**
     * 获取单个值，无缓存
     *
     * @return
     */
    @Override
    public String getNoCache(String key) {
        return configDbDao.get(key);
    }

    /**
     * get子项
     * 缓存失效时间: after call set
     *
     * @param path
     * @return
     */
    @Override
    @Cacheable(value = ConfigDbConstant.CONFIG_CACHE_M, key = "#path.hashCode()")
    public Map<String, String> gets(String path) {
        return list2map(configDbDao.gets(path));
    }

    /**
     * 获取多个
     * 缓存失效时间: after call set
     *
     * @param keys
     * @return
     */
    @Override
    @Cacheable(value = ConfigDbConstant.CONFIG_CACHE_MK, key = "#keys.toString()")
    public Map<String, String> getBatch(List<String> keys) {
        return list2map(configDbDao.getBatch(keys));
    }


    private static Map<String, String> list2map(List<Map<String, String>> list) {
        Map<String, String> res = new HashMap<>();
        if (CollectionUtil.isEmpty(list)) {
            return res;
        }
        for (Map<String, String> item : list) {
            res.put(item.get(ConfigDbConstant.KEY), item.get(ConfigDbConstant.VALUE));
        }
        return res;
    }
}
