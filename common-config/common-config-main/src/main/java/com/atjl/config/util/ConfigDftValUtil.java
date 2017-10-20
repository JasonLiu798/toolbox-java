package com.atjl.config.util;


import com.atjl.util.character.StringCheckUtil;
import com.atjl.util.collection.CollectionUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ConfigDftValUtil {
    private ConfigDftValUtil() {
        super();
    }


    /**
     * 如果原始值不存在则添加原始值
     * @param rawVal
     * @param dftVals
     * @return
     */
    public static Map<String, String> rawFilterAddDft(Map<String, String> rawVal, Map<String, String> dftVals) {
        if (CollectionUtil.isEmpty(rawVal)) {
            return dftVals;
        }
        Map<String, String> res = rawVal;
        List<String> keys = new ArrayList<>();
        keys.addAll(rawVal.keySet());
        //set default values
        for (Map.Entry<String, String> entry : dftVals.entrySet()) {
            if (StringCheckUtil.isEmpty(rawVal.get(entry.getKey()))) {
                res.put(entry.getKey(), entry.getValue());
            }
        }
        return res;
    }

}
