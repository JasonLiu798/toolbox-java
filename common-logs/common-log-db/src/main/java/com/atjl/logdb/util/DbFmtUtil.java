package com.atjl.logdb.util;


import com.atjl.util.collection.CollectionUtil;
import com.atjl.util.json.JSONFastJsonUtil;

public class DbFmtUtil {
    private DbFmtUtil() {
        throw new UnsupportedOperationException();
    }

    public static Object[] filter2Json(Object... params) {
        if (CollectionUtil.isEmpty(params)) {
            return params;
        }
        Object[] strParam = new Object[params.length];
        for (int i = 0; i < params.length; i++) {
            String objStr = null;
            objStr = "[J]" + JSONFastJsonUtil.objectToJson(params[i]);
            //发送exception，使用string
            //objStr = "[S]" + params[i].toString();
            strParam[i] = objStr;
        }
        return strParam;
    }
}
