package com.atjl.configdb.api;

import com.atjl.util.character.StringCheckUtil;
import com.atjl.util.collection.CollectionUtil;
import com.atjl.utilex.ApplicationContextHepler;
import com.atjl.util.common.ReflectUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * db配置读取
 */
public class ConfigDbUtil {
    private ConfigDbUtil() {
        super();
    }

    private static Object configDbService;

    /**
     * 获取
     *
     * @return
     */
    private static synchronized Object getConfDbService() {
        if (configDbService == null) {
            configDbService = ApplicationContextHepler.getBeanByName(ConfigDbConstant.CONF_DB_SERVICE);
        }
        return configDbService;
    }

    /**
     * 初步校验
     *
     * @param param
     * @return
     */
    private static boolean preChk(Object param) {
        if (param == null) {
            return false;
        }
        if ((param instanceof String) || (param instanceof List)) {
            if ((param instanceof String) && StringCheckUtil.isEmpty(param)) {
                return false;
            }
            if ((param instanceof List) && CollectionUtil.isEmpty((List) param)) {
                return false;
            }
        } else {
            return false;
        }
        configDbService = getConfDbService();
        if (configDbService == null) {
            return false;
        }
        return true;
    }


    /**
     * 获取 一个 配置
     *
     * @param pk
     * @return
     */
    public static String get(String pk) {
        if (!preChk(pk)) {
            return null;
        }
        Object raw = ReflectUtil.invokeMethod(configDbService, ConfigDbConstant.METHOD_GET, new Class[]{String.class}, new Object[]{pk});
        if (raw != null) {
            return String.valueOf(raw);
        }
        return null;
    }

    /**
     * 获取子节点下多个
     *
     * @param path
     * @return
     */
    public static Map<String, String> gets(String path) {
        Map<String, String> res = new HashMap<>();
        if (!preChk(path)) {
            return res;
        }

        Object raw = ReflectUtil.invokeMethod(configDbService, ConfigDbConstant.METHOD_GETS, new Class[]{String.class}, new Object[]{path});
        if (raw != null) {
            return (Map<String, String>) raw;
        }
        return res;
    }

    /**
     * 获取 指定key 多个
     *
     * @param keys
     * @return
     */
    public static Map<String, String> getBatch(List<String> keys) {
        Map<String, String> res = new HashMap<>();
        if (!preChk(keys)) {
            return res;
        }
        Object raw = ReflectUtil.invokeMethod(configDbService, ConfigDbConstant.METHOD_GET_BATCH, new Class[]{List.class}, new Object[]{keys});
        if (raw != null) {
            return (Map<String, String>) raw;
        }
        return res;
    }


}
