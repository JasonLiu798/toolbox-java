package com.atjl.util.config.util;


import com.atjl.util.character.StringCheckUtil;
import com.atjl.util.config.ConfigPropConstant;

import java.util.Properties;

public class ConfigPropCommonUtil {
    private ConfigPropCommonUtil() {
        super();
    }

    /**
     * filter of key prefix
     *
     * @param prefix key prefix
     * @param key    key
     * @return filted key
     */
    public static String filterPrefix(String prefix, String key) {
        if (!StringCheckUtil.isEmpty(prefix)) {
            key = prefix + "." + key;
        }
        return key;
    }

    /**
     * get string from properties
     *
     * @param prop   properteies
     * @param prefix prefix
     * @param key    key
     * @return String value
     */
    public static String getStringFromProperties(Properties prop, String prefix, String key) {
        Object obj = prop.get(filterPrefix(prefix, key));
        if (obj == null) {
            return null;
        }
        return String.valueOf(obj);
    }

    public static String getStringFromProperties(Properties prop, String key) {
        return getStringFromProperties(prop, ConfigPropConstant.DFT_PREFIX, key);
    }

}
