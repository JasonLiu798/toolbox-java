package com.jason798.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import java.util.HashMap;
import java.util.Map;

/**
 * json helper
 * user alibaba fastjson
 */
public class JSONFastJsonUtil {

	@SuppressWarnings({"rawtypes","unchecked"})
	public static Map<String ,Object> jsonToObject(String jsonStr) {
        Map jsonObject = JSON.parseObject(jsonStr);
        Map<String, Object> outMap = new HashMap<String, Object>();
        for (Object o : jsonObject.entrySet()) {
            Map.Entry<String, String> entry = (Map.Entry<String, String>) o;
            outMap.put(entry.getKey(), entry.getValue());
        }
        return outMap;
    }

	public static String objectToJson(Object object){
		return JSON.toJSONString(object) ;
	}
	
	public static <T> T jsonToObject(String jsonStr, Class<T> clazz){        
		JSONObject json = JSONObject.parseObject(jsonStr);
		return JSONObject.toJavaObject(json, clazz);
    } 
	
}