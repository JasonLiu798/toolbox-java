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
 * 注: configDbServcie初始化后，不会更换；一般情况只会使用一种type
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
    private static synchronized Object getConfDbService(String type) {
        if (configDbService == null) {
            configDbService = ApplicationContextHepler.getBeanByName(type);
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
        return true;
    }
    
    private static boolean init(String type){
		configDbService = getConfDbService(type);
		if (configDbService == null) {
			return false;
		}
		return true;
	}


    /**
     * 获取 value
     *
	 * @param type
     * @param pk
     * @return
     */
    public static String get(String type,String pk) {
        if (!preChk(pk)) {
            return null;
        }
        if(!init(type)){
        	return null;
		}
        Object raw = ReflectUtil.invokeMethod(configDbService, ConfigDbConstant.METHOD_GET, new Class[]{String.class}, new Object[]{pk});
        if (raw != null) {
            return String.valueOf(raw);
        }
        return null;
    }
    
	/**
	 * 获取 指定key 多个
	 *
	 * @param keys
	 * @return
	 */
	public static Map<String, String> getBatch(String type,List<String> keys) {
		Map<String, String> res = new HashMap<>();
		if (!preChk(keys)) {
			return res;
		}
		if(!init(type)){
			return res;
		}
		Object raw = ReflectUtil.invokeMethod(configDbService, ConfigDbConstant.METHOD_GET_BATCH, new Class[]{List.class}, new Object[]{keys});
		if (raw != null) {
			return (Map<String, String>) raw;
		}
		return res;
	}
	
	
    /**
     * 获取子节点下多个
     *
     * @param path
     * @return
     *
    public static Map<String, String> gets(String type,String path) {
        Map<String, String> res = new HashMap<>();
        if (!preChk(path)) {
            return res;
        }
        if(!init(type)){
        	return res;
		}

        Object raw = ReflectUtil.invokeMethod(configDbService, ConfigDbConstant.METHOD_GETS, new Class[]{String.class}, new Object[]{path});
        if (raw != null) {
            return (Map<String, String>) raw;
        }
        return res;
    }*/




}
