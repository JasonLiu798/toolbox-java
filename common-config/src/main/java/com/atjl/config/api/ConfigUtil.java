package com.atjl.config.api;

import com.atjl.config.util.ConfigDftValUtil;
import com.atjl.util.character.StringCheckUtil;
import com.atjl.util.collection.CollectionUtil;
import com.atjl.util.config.ConfigPropUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 配置工具
 */
public class ConfigUtil {
    private static final Logger logger = LoggerFactory.getLogger(ConfigUtil.class);

    private ConfigUtil() {
        super();
    }

    /**
     * 默认使用 tm_config表实现
     */
    private static final String DFT_TP = ConfigConstant.CONF_SERVICE_USE_DB_PLAIN;

    /**
     * 获取一个
     * 默认从 db取
     *
     * @param key
     * @return
     */
    public static String get(String key) {
        return get(DFT_TP, key, null);
    }

    /**
     * 获取一个，不存在则使用默认
     * 默认从 db取
     *
     * @param type
     * @param key
     * @return
     */
    public static String get(String type, String key) {
        return get(type, key, null);
    }

    /**
     * 获取一个
     *
     * @param type
     * @param key
     * @param dftVal
     * @return
     */
    public static String get(String type, String key, String dftVal) {
        String rawV = null;
        switch (type) {
            case ConfigConstant.CONF_SERVICE_USE_DB_PLAIN:
            case ConfigConstant.CONF_SERVICE_USE_DB_TREE:
                rawV = ConfigDbUtil.get(type, key);
                break;
            case ConfigConstant.CONF_SERVICE_PROP:
                rawV = ConfigPropUtil.get(key);
                break;
            default:
                logger.error("unknown config source,{}", type);
                break;
        }
        if (StringCheckUtil.isEmpty(rawV)) {
            if (logger.isInfoEnabled()) {
                logger.info("config get raw null,type {},key {},dft {}", type, key, dftVal);
            }
            return dftVal;
        }
        return rawV;
    }

    /**
     * 批量获取，有默认值
     *
     * @param type
     * @param keyAndDfts
     * @return
     */
    public static Map<String, String> getBatch(String type, Map<String, String> keyAndDfts) {
        if (CollectionUtil.isEmpty(keyAndDfts)) {
            return new HashMap<>();
        }
        switch (type) {
            case ConfigConstant.CONF_SERVICE_USE_DB_PLAIN:
            case ConfigConstant.CONF_SERVICE_USE_DB_TREE:
                return ConfigDftValUtil.rawFilterAddDft(ConfigDbUtil.getBatch(type, CollectionUtil.map2list(keyAndDfts, true)), keyAndDfts);
            case ConfigConstant.CONF_SERVICE_PROP:
                return ConfigDftValUtil.rawFilterAddDft(ConfigPropUtil.getBatch(CollectionUtil.map2list(keyAndDfts, true)), keyAndDfts);
            default:
                logger.error("unknown config source,{}", type);
                return null;
        }
    }

    public static Map<String, String> getBatch(Map<String, String> keyAndDfts) {
        return getBatch(DFT_TP, keyAndDfts);
    }

    /**
     * 批量获取无默认值
     *
     * @param type
     * @param keys
     * @return
     */
    public static Map<String, String> getBatch(String type, List<String> keys) {
        if (CollectionUtil.isEmpty(keys)) {
            return new HashMap<>();
        }
        switch (type) {
            case ConfigConstant.CONF_SERVICE_USE_DB_PLAIN:
            case ConfigConstant.CONF_SERVICE_USE_DB_TREE:
                return ConfigDbUtil.getBatch(type, keys);
            case ConfigConstant.CONF_SERVICE_PROP:
                return ConfigPropUtil.getBatch(keys);
            default:
                logger.error("unknown config source,{}", type);
                return null;
        }
    }
}
