package com.atjl.util.reflect;


import com.atjl.util.character.StringCheckUtil;
import com.atjl.util.character.StringUtil;

import java.util.HashMap;
import java.util.Map;

public class ReflectCommonUtil {
    private ReflectCommonUtil() {
        throw new UnsupportedOperationException();
    }


    /**
     * is empty
     *
     * @param obj object
     * @return is empty or not
     */
    public static boolean isEmpty(Object obj) {
        if (obj == null)
            return true;
        return false;
    }

    /**
     * covert to String
     *
     * @param paramMap feild - value Object map
     * @return field-value String
     */
    public static Map<String, String> convert(Map<String, Object> paramMap) {
        Map<String, String> params = new HashMap<>();
        if (paramMap == null) {
            return null;
        }
        for (Map.Entry<String, Object> entry : paramMap.entrySet()) {
            String mapKey = entry.getKey();
            Object value = entry.getValue();
            params.put(mapKey, value == null ? "" : value.toString());
        }
        return params;
    }

    /**
     * generate Get method
     *
     * @param field
     * @return String
     */
    public static String generateGetName(String field, boolean isBoolean) {
        if (StringCheckUtil.isEmpty(field)) {
            return null;
        }
        String upperFiled = StringUtil.toUpperCaseFirstOne(field);
        if (isBoolean) {
            return ReflectConstant.GET_BOOL_PREFIX + upperFiled;
        }
        return ReflectConstant.GET_PREFIX + upperFiled;
    }

}
