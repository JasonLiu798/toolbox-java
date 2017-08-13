package com.atjl.config.api;

import com.atjl.config.util.ConfigDftValUtil;
import com.atjl.configdb.api.ConfigDbUtil;
import com.atjl.util.character.StringCheckUtil;
import com.atjl.util.collection.CollectionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * 配置工具
 */
public class ConfigUtil {
    private static final Logger logger = LoggerFactory.getLogger(ConfigUtil.class);

    private ConfigUtil() {
        super();
    }

    public static final String DFT_TP = ConfigConstant.CONF_SERVICE_USE_DICT;

    /**
     * 获取一个
     * 默认从 db取
     * @param key
     * @return
     */
    public static String get(String key) {
        return get(DFT_TP, key, null);
    }

    /**
     * 获取一个，不存在则使用默认
     * 默认从 db取
     * @param key
     * @param dftVal
     * @return
     */
    public static String get(String key, String dftVal) {
        return get(DFT_TP, key, dftVal);
    }

    /**
     * 获取一个
     * @param type
     * @param key
     * @param dftVal
     * @return
     */
    public static String get(String type, String key, String dftVal) {
        switch (type) {
            case ConfigConstant.CONF_SERVICE_USE_DICT:
                String v = ConfigDbUtil.get(key);
                return StringCheckUtil.isEmpty(v) ? dftVal : v;
            case ConfigConstant.CONF_SERVICE_PROP:
                return null;
            default:
                logger.error("unknown config source,{}", type);
                return null;
        }
    }



    /**
     * 获取多个子项
     * @param type
     * @param key
     * @return
     */
    public static Map<String, String> gets(String type, String key) {
        switch (type) {
            case ConfigConstant.CONF_SERVICE_USE_DICT:
                return ConfigDbUtil.gets(key);
            case ConfigConstant.CONF_SERVICE_PROP:
                return null;
            default:
                logger.error("unknown config source,{}", type);
                return null;
        }
    }
    public static Map<String, String> gets(String key) {
        return gets(DFT_TP, key);
    }


    /**
     * 批量获取，有默认值
     * @param type
     * @param keyAndDfts
     * @return
     */
    public static Map<String, String> getBatch(String type, Map<String, String> keyAndDfts) {
        if (CollectionUtil.isEmpty(keyAndDfts)) {
            return new HashMap<>();
        }
        switch (type) {
            case ConfigConstant.CONF_SERVICE_USE_DICT:
                return ConfigDftValUtil.rawFilterAddDft(ConfigDbUtil.getBatch(CollectionUtil.map2list(keyAndDfts,true)), keyAndDfts);
            case ConfigConstant.CONF_SERVICE_PROP:
                return null;
            default:
                logger.error("unknown config source,{}", type);
                return null;
        }
    }

    public static Map<String, String> getBatch(Map<String, String> keyAndDfts) {
        return getBatch(DFT_TP, keyAndDfts);
    }


}
