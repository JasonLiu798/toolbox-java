package com.atjl.util.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * json helper
 * user alibaba fastjson
 */
public class JSONFastJsonUtil {

    public static Map<String, Object> jsonToMapObj(String jsonStr) {
        return JSON.parseObject(jsonStr);
    }

    /**
     * @param jsonStr
     * @return
     */
    public static Map<String, String> jsonToMap(String jsonStr) {
        Map jsonObject = JSON.parseObject(jsonStr);
        Map<String, String> outMap = new HashMap<>();
        for (Object o : jsonObject.entrySet()) {
            Map.Entry<String, String> entry = (Map.Entry<String, String>) o;
            outMap.put(entry.getKey(), entry.getValue());
        }
        return outMap;
    }

    public static <T> T jsonToObject(String jsonStr, Class<T> clazz) {
        JSONObject json = JSONObject.parseObject(jsonStr);
        return JSONObject.toJavaObject(json, clazz);
    }

    public static String objectToJson(Object object) {
        if (object == null) {
            return "";
        }
        return JSON.toJSONString(object);
    }

    public static String objectToJsonNoException(Object object) {
        try {
            return JSON.toJSONString(object);
        } catch (Exception e) {
            return "null";
        }
    }

    // select SUBSTRING(BASIC, INSTR(BASIC, '"guardian":"' )+12, LOCATE('"', BASIC, INSTR(BASIC, '"guardian":"')+13) - (INSTR(BASIC, '"guardian":"' )+12)) from

    public static String getSimpleContent(String json, String key) {
        return null;
    }


}